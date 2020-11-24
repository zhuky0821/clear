package zhuky.clear.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuky.clear.service.InitService;
import zhuky.clear.util.FileUtil;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class InitServiceImpl implements InitService {
    private static final Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private DataSource dataSource;

    @Override
    public void initSchema() throws Exception {
        logger.info("处理表结构重建开始");

        try {
            logger.debug("读取建表脚本开始");
            File file = fileUtil.getClassPathFile("db/schema.sql");
            String schemaSql = fileUtil.readFileByLines(file);
            logger.debug("建表脚本{}",  schemaSql);

            Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(schemaSql);

        } catch (IOException e) {
            logger.error("找不到建表脚本文件");
            throw e;
        } catch (SQLException e) {
            logger.error("执行建表sql错误，错误信息：{}", e.getMessage());
            throw e;
        }

        logger.info("处理表结构重建结束");
    }
}
