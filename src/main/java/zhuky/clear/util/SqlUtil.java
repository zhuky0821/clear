package zhuky.clear.util;

import org.apache.ignite.client.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.IgniteConfig;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class SqlUtil {
    private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    IgniteConnUtil igniteConnUtil;

    public void execSqlFile(String path) throws IOException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            logger.debug("读取脚本文件内容开始");
            File file = fileUtil.getClassPathFile(path);
            String fileSql = fileUtil.readFileByLines(file);
            logger.debug("脚本文件内容：{}",  fileSql);

            connection = igniteConnUtil.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(fileSql);

        } catch (IOException e) {
            logger.error("找不到脚本文件");
            throw e;
        } catch (SQLException e) {
            logger.error("执行脚本文件错误，错误信息：{}", e.getMessage());
            throw e;
        }
        finally {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }


}
