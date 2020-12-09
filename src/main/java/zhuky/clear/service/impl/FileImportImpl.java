package zhuky.clear.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuky.clear.exception.BusinessErrorException;
import zhuky.clear.service.FileImport;
import zhuky.clear.util.IgniteConnUtil;
import zhuky.clear.util.ORMUtil;
import zhuky.clear.util.StringUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class FileImportImpl implements FileImport {
    private static final Logger logger = LoggerFactory.getLogger(FileImportImpl.class);
    @Autowired
    private ORMUtil ormUtil;

    @Autowired
    IgniteConnUtil igniteConnUtil;

    @Override
    public void importFile(String filePath, String tableName) {
        logger.info("导入文件{}开始", filePath);
        /**
         * String copySql = "COPY FROM 'c:\\Users\\zhuky\\Desktop\\1111.csv' INTO tjsmx (\n" +
         * 					"  SCDM,....,RECORD_ID\n) FORMAT CSV\n";
         */
        String classFullName = "zhuky.clear.entity." + StringUtil.toUpperCaseFirstOne(tableName);
        StringBuilder copySql = new StringBuilder();
        copySql.append("COPY FROM \'").append(filePath).append("\' INTO ").append(tableName).append(" (")
                .append(ormUtil.getSql(classFullName)).append(") FORMAT CSV");
        logger.info("导入语句：{}", copySql.toString());
        Connection connection = null;
        Statement statement = null;
        try {
            connection = igniteConnUtil.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(copySql.toString());
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("文件导入失败！文件名：{}，错误信息：{}", filePath, e.getMessage());
            throw new BusinessErrorException("1006", "执行COPY命令出错" + e.getMessage());
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("导入文件{}结束", filePath);
    }
}
