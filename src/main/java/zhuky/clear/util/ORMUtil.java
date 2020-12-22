package zhuky.clear.util;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.ClearContext;
import zhuky.clear.exception.BusinessErrorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class ORMUtil {
    private static final Logger logger = LoggerFactory.getLogger(ORMUtil.class);

    @Autowired
    private ClearContext clearContext;
    @Autowired
    private IgniteCache igniteCache;

    public <E> E convert2Object(List<?> list, Class<E> clazz) {
        //logger.trace("对象转换{}", list);
        E res = null;

        try {
            Field[] declaredFields = clazz.getDeclaredFields();
            res = clazz.newInstance();
            for(int i=0; i<declaredFields.length; i++){
                Field field = declaredFields[i];
                field.setAccessible(true);
                field.set(res, list.get(i));
            }
        } catch (Exception e) {
            logger.error("查询结果反射出对象出现异常：{}", e.getMessage());
            throw new BusinessErrorException("1000", "查询结果反射出对象出现异常：" + e.getMessage());
        }

        return res;
    }


    public String getSql(Class clazz){
        StringBuilder sql = new StringBuilder();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(int i=0; i<declaredFields.length; i++){
            sql.append(StringUtil.camelToUnderline(declaredFields[i].getName(), 1));
            if(i < declaredFields.length -1){
                sql.append(",");
            }
        }
        return sql.toString();
    }

    public <E> List<E> queryAll(String sql, Class<E> clazz, Object... args){

        List<E> res = new ArrayList<>();
        List<List<?>> all = igniteCache.query(new SqlFieldsQuery(sql).setArgs(args)).getAll();
        for (List<?> objects : all) {
            E e = convert2Object(objects, clazz);
            res.add(e);
        }
        return res;
    }

    public <E> List<E> querySingleTable(Class<E> clazz, String condition, Object... args){
        StringBuilder sqlBulider = new StringBuilder();
        sqlBulider.append("select ").append(getSql(clazz)).append(" from ").append(clazz.getSimpleName());
        if(condition != null && condition.trim().length() > 0){
            sqlBulider.append(" where ").append(condition);
        }
        String sql = sqlBulider.toString();
        logger.trace("执行单表查询sql：{}", sql);
        return queryAll(sql, clazz, args);
    }


}
