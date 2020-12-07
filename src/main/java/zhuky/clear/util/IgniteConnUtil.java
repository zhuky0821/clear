package zhuky.clear.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.IgniteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class IgniteConnUtil {

    @Autowired
    private IgniteConfig igniteConfig;

    public Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            connection = DriverManager.getConnection("jdbc:ignite:thin://"+igniteConfig.getIgniteAddress());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
