package zhuky.clear.dao.impl;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.dao.CommonDbMapper;
import zhuky.clear.util.StringUtil;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class CommonDbMapperImpl implements CommonDbMapper {
    private static final Logger logger = LoggerFactory.getLogger(CommonDbMapperImpl.class);
    @Autowired
    IgniteCache igniteCache;

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
    public void insert(Object data, String insertSql) {
        logger.trace("对象执行插入{}", data);
        if(insertSql == null){
            insertSql = getInsertSql(data);
        }
        Class clazz = data.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Object[] values = new Object[declaredFields.length];
        for(int i=0; i<declaredFields.length; i++){
            Field field = declaredFields[i];
            field.setAccessible(true);
            try {
                values[i] = field.get(data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        update(insertSql, values);
    }

    /**
     * 单条数据插入，使用SqlFieldsQuery的方式
     * @param data
     */
    @Override
    public void insert(Object data) {
        insert(data, null);
    }

    @Override
    public void insertBatch(List<?> data) {
        logger.debug("批量插入数据开始");
        if(data.size() == 0) return;
        String insertSql = getInsertSql(data.get(0));

        for(int i=0; i<data.size(); i++){
            insert(data.get(i), insertSql);
        }

        logger.debug("批量插入数据结束，插入条数：{}", data.size());
    }

    /**
     * 获取insert的sql语句，需要注入变量
     * @param data
     * @return
     */
    public String getInsertSql(Object data){
        Class clazz = data.getClass();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into ").append(clazz.getSimpleName()).append(" (");

        StringBuilder valueBuilder = new StringBuilder();

        Field[] declaredFields = clazz.getDeclaredFields();
        for(int i=0; i<declaredFields.length; i++){
            Field field = declaredFields[i];
            sqlBuilder.append(StringUtil.camelToUnderline(field.getName(), 1));
            valueBuilder.append("?");
            if(i < declaredFields.length -1){
                sqlBuilder.append(",");
                valueBuilder.append(",");
            }
        }
        sqlBuilder.append(") values (").append(valueBuilder).append(")");
        return sqlBuilder.toString();
    }


}
