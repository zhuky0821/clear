package zhuky.clear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.IgniteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class IgniteConnUtil {
    private static final Logger logger = LoggerFactory.getLogger(IgniteConnUtil.class);
    @Autowired
    private IgniteConfig igniteConfig;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            connection = DriverManager.getConnection("jdbc:ignite:thin://"+igniteConfig.getIgniteAddress());
        } catch (ClassNotFoundException e) {
            logger.error("找不到Ignite连接库");
            throw e;
        } catch (SQLException e) {
            logger.error("Ignite连接失败,地址：{}", igniteConfig.getIgniteAddress());
            throw e;
        }

        return connection;
    }

}
