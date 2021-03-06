package zhuky.clear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.ClearContext;
import zhuky.clear.exception.BusinessErrorException;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class SqlUtil {
    private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    ClearContext clearContext;

    public void execSqlFile(String path){
        List<File> allFiles = fileUtil.getAllFiles(path);
        for (File file : allFiles) {
            execSingleSqlFile(file);
        }
    }

    public void execSingleSqlFile(File file){

        Connection connection = null;
        Statement statement = null;
        try {
            logger.debug("读取脚本文件{}内容开始", file.getName());
            String fileSql = fileUtil.readFileByLines(file);
            logger.debug("脚本文件内容：{}",  fileSql);

            connection = clearContext.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(fileSql);

        } catch (Exception e) {
            logger.error("执行脚本文件错误，错误信息：{}", e.getMessage());
            throw new BusinessErrorException("1005", "执行脚本文件错误，错误信息：" + e.getMessage());
        }
        finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("statement关闭失败");
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("connection关闭失败");
                }
            }
        }
    }


    public void truncateTable(String name){
        logger.debug("begin truncate table {}", name);
        File file = fileUtil.getSqlFile(name);
        execSingleSqlFile(file);
        logger.debug("end truncate table {}", name);
    }

}
