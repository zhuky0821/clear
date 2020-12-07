package zhuky.clear.util;

import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ORMUtil {
    private static final Logger logger = LoggerFactory.getLogger(ORMUtil.class);

    @Autowired
    private IgniteClient client;

    public <E> E convertTObject(List<?> list, String className){
        E res = null;

        try {
            Class clazz = Class.forName(className);
            Constructor allFieldsConstruct = null;
            Field[] declaredFields = clazz.getDeclaredFields();
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                if(constructor.getParameterCount() == declaredFields.length){
                    allFieldsConstruct = constructor;
                    break;
                }
            }
            if(allFieldsConstruct == null){
                throw new RuntimeException("没有找到全参构造器");
            }

            res = (E) allFieldsConstruct.newInstance(list.toArray());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
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
            E e = convertTObject(objects, classFullName);
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
        logger.info("执行单表查询sql：{}", sql);
        return queryAll(sql, classFullName, args);
    }
}
