package zhuky.clear.config;


import org.apache.ignite.springframework.boot.autoconfigure.IgniteClientConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zhuky.clear.entity.Tsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 清算中心上下文
 * 1.提供线程池
 * 2.提供获取数据库连接方法
 */
@Configuration
public class ClearContext {
    private static final Logger logger = LoggerFactory.getLogger(ClearContext.class);

    @Value("${ignite-client.addresses}")
    private String igniteAddress;
    @Value("${clear.threadpool.size}")
    private int threadPoolSize;

    private ExecutorService executorService;

    /**
     * 本地缓存池
     */
    private Map<String, Tsecurity> tsecurityCodeMktCache;

    public Map<String, Tsecurity> getTsecurityCodeMktCache() {
        if(tsecurityCodeMktCache == null){
            tsecurityCodeMktCache = new HashMap<>();
        }
        return tsecurityCodeMktCache;
    }

    /**
     * 获取线程池
     * @return 定长线程池
     */
    public ExecutorService getExecutorService() {
        if(executorService == null){
            executorService = Executors.newFixedThreadPool(threadPoolSize);
        }
        return executorService;
    }

    /**
     * Ignite瘦客户端配置
     * @return
     */
    @Bean
    IgniteClientConfigurer configurer() {
        //其他配置使用yml配置
        return cfg -> cfg.setSendBufferSize(64*1024);
    }

    /**
     * 获取Ignite JDBC连接，基本的数据库操作使用ignite瘦客户端来完成，jdbc连接供初始化脚本和执行COPY命令进行数据导入
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            connection = DriverManager.getConnection("jdbc:ignite:thin://" + igniteAddress);
        } catch (ClassNotFoundException e) {
            logger.error("找不到Ignite连接库");
            throw e;
        } catch (SQLException e) {
            logger.error("Ignite连接失败,地址：{}", igniteAddress);
            throw e;
        }

        return connection;
    }


}
