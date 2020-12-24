package zhuky.clear.dao.impl;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.ClearContext;
import zhuky.clear.dao.CommonDbMapper;
import zhuky.clear.exception.BusinessErrorException;
import zhuky.clear.util.StringUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommonDbMapperImpl implements CommonDbMapper {
    private static final Logger logger = LoggerFactory.getLogger(CommonDbMapperImpl.class);
    @Autowired
    IgniteCache igniteCache;
    @Autowired
    ClearContext clearContext;

    @Override
    public List<List<?>> commonQuery(String sql, Object... args) {
        logger.trace("执行通用查询sql{}，参数：{}", sql, args);
        return igniteCache.query(new SqlFieldsQuery(sql).setArgs(args)).getAll();
    }

    @Override
    public void update(String sql, Object... args) {
        logger.trace("执行通用修改sql{}，参数：{}", sql, args);
        igniteCache.query(new SqlFieldsQuery(sql).setArgs(args)).getAll();
    }

    /**
     * 单条数据插入，使用SqlFieldsQuery的方式
     * @param data
     */
    @Override
    public void insert(Object data) {
        logger.trace("对象执行插入{}", data);
        Class clazz = data.getClass();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into ").append(clazz.getSimpleName()).append(" (");

        StringBuilder valueBuilder = new StringBuilder();

        Field[] declaredFields = clazz.getDeclaredFields();
        Object[] values = new Object[declaredFields.length];
        for(int i=0; i<declaredFields.length; i++){
            Field field = declaredFields[i];
            field.setAccessible(true);
            sqlBuilder.append(StringUtil.camelToUnderline(field.getName(), 1));
            try {
                valueBuilder.append("?");
                values[i] = field.get(data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(i < declaredFields.length -1){
                sqlBuilder.append(",");
                valueBuilder.append(",");
            }
        }

        sqlBuilder.append(") values (").append(valueBuilder).append(")");
        update(sqlBuilder.toString(), values);
    }

    @Override
    public void insertBatch(List<?> data) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = clearContext.getConnection();
            statement = connection.createStatement();
            List<String> allSql = getAllSql(data);
            for (String s : allSql) {
                statement.executeUpdate(s);
            }
        } catch (Exception e) {
            logger.error("批量插入失败。{}",e.getMessage());
            new BusinessErrorException("-1", "批量插入失败，错误信息：" + e.getMessage());
        }
    }

    /**
     * 每1000条数据得到一个sql
     * @param data
     * @return
     */
    public List<String> getAllSql(List<?> data){
        if(data.size() == 0) return null;
        Class clazz = data.get(0).getClass();
        List<String> allSql = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder();
        Field[] declaredFields = clazz.getDeclaredFields();
        int count = 0;
        for (Object o : data) {
            StringBuilder valueBuilder = new StringBuilder();
            sqlBuilder.append("insert into ").append(clazz.getSimpleName()).append(" (");
            for(int i=0; i<declaredFields.length; i++){
                Field field = declaredFields[i];
                field.setAccessible(true);
                sqlBuilder.append(StringUtil.camelToUnderline(field.getName(), 1));
                try {
                    valueBuilder.append("'").append(field.get(o)).append("'");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(i < declaredFields.length -1){
                    sqlBuilder.append(",");
                    valueBuilder.append(",");
                }
            }
            sqlBuilder.append(") values (").append(valueBuilder).append(");");
            count ++;
            if(count == 1000){
                allSql.add(sqlBuilder.toString());
                count = 0;
            }
        }
        if(count > 0){
            allSql.add(sqlBuilder.toString());
        }
        return allSql;
    }


}
