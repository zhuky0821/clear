package zhuky.clear.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteSystemProperties;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.WALMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    //@Value("${ignite-client.addresses}")
    private String igniteAddress = "localhost:10800";
    @Value("${clear.threadpool.size}")
    private int threadPoolSize;
    @Value("${clear.ignite.persistence}")
    private boolean persistence;

    @Autowired
    @Lazy
    private Ignite ignite;

    @Bean
    @Lazy
    IgniteCache getIgniteCache() {
        if(!ignite.cluster().active() && persistence){
            //开启原生持久化之后，借点默认是关闭的，要手工开启
            ignite.cluster().active(true);
        }
        return ignite.getOrCreateCache("clear");
    }

    /**
     * 获取线程池
     * @return 定长线程池
     */
    @Bean
    @Lazy
    ExecutorService getExecutorService() {
        return Executors.newFixedThreadPool(threadPoolSize);
    }

    /**
     * Ignite瘦客户端配置
     * @return
     */
//    @Bean
//    IgniteClientConfigurer configurer() {
//        //其他配置使用yml配置
//        return cfg -> cfg.setSendBufferSize(64*1024);
//    }

    @Bean
    public IgniteConfiguration igniteConfiguration() {
        // If you provide a whole ClientConfiguration bean then configuration properties will not be used.
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setIgniteInstanceName("clear");
        //开启持久化之后对性能影响很大
        if(persistence){
            //开启原生持久化
            DataStorageConfiguration dataStorageConfiguration = new DataStorageConfiguration();
            dataStorageConfiguration.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
            dataStorageConfiguration.setWalMode(WALMode.BACKGROUND);
            cfg.setDataStorageConfiguration(dataStorageConfiguration);
        }
        return cfg;
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
