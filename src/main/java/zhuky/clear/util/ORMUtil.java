package zhuky.clear.util;

import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.exception.BusinessErrorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class ORMUtil {
    private static final Logger logger = LoggerFactory.getLogger(ORMUtil.class);

    @Autowired
    private IgniteClient client;

    public <E> E convert2Object(List<?> list, String className) {
        E res = null;

        try {
            Class clazz = Class.forName(className);
            Field[] declaredFields = clazz.getDeclaredFields();
            Constructor constructor = clazz.getConstructor();

            res = (E) constructor.newInstance();
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


    public String getSql(String classFullName){
        StringBuilder sql = new StringBuilder();
        try {
            Class clazz = Class.forName(classFullName);

            Field[] declaredFields = clazz.getDeclaredFields();
            for(int i=0; i<declaredFields.length; i++){
                sql.append(StringUtil.camelToUnderline(declaredFields[i].getName(), 1));
                if(i < declaredFields.length -1){
                    sql.append(",");
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sql.toString();
    }

    public <E> List<E> queryAll(String sql, String classFullName, Object... args){

        List<E> res = new ArrayList<>();
        List<List<?>> all = client.query(new SqlFieldsQuery(sql).setArgs(args)).getAll();
        for (List<?> objects : all) {
            E e = convert2Object(objects, classFullName);
            res.add(e);
        }
        return res;
    }


    public <E> List<E> querySingleTable(String className, String condition, Object... args){
        String classFullName = "zhuky.clear.entity." + className;
        StringBuilder sqlBulider = new StringBuilder();
        sqlBulider.append("select ").append(getSql(classFullName)).append(" from ").append(className);
        if(condition != null && condition.trim().length() > 0){
            sqlBulider.append(" where ").append(condition);
        }
        String sql = sqlBulider.toString();
        logger.trace("执行单表查询sql：{}", sql);
        return queryAll(sql, classFullName, args);
    }
}
