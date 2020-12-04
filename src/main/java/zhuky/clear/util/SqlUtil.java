package zhuky.clear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class SqlUtil {
    private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private FileUtil fileUtil;

    public void execSqlFile(String path) throws IOException, SQLException {
//        try {
//            logger.debug("读取脚本文件内容开始");
//            File file = fileUtil.getClassPathFile(path);
//            String fileSql = fileUtil.readFileByLines(file);
//            logger.debug("脚本文件内容：{}",  fileSql);
//
//            Connection conn = dataSource.getConnection();
//            Statement statement = conn.createStatement();
//            statement.executeUpdate(fileSql);
//
//        } catch (IOException e) {
//            logger.error("找不到脚本文件");
//            throw e;
//        } catch (SQLException e) {
//            logger.error("执行脚本文件错误，错误信息：{}", e.getMessage());
//            throw e;
//        }
    }
}
