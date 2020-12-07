package zhuky.clear;

import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.entity.Tbond;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.util.ORMUtil;
import zhuky.clear.util.StringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SpringBootTest
public class QueryTest {

    @Autowired
    BaseTableQueryMapper baseTableQueryMapper;

    @Test
    void testqueryShareholder(){
        List<Tshareholder> productUseShareholder = baseTableQueryMapper.getProductUseShareholder(1);
        System.out.println(productUseShareholder);

        Tsecurity security = baseTableQueryMapper.getSecurity("600001", 1);
        System.out.println(security);
    }

    @Autowired
    IgniteClient client;

    @Test
    void createJsmxColumn(){
        SqlFieldsQuery sqlFieldsQuery = new SqlFieldsQuery("SELECT a.column_name FROM SYS.TABLE_COLUMNS a WHERE a.table_name = ? AND a.TYPE NOT IS null").setArgs("TJSMX");
        List<List<?>> all = client.query(sqlFieldsQuery).getAll();
        for (List<?> objects : all) {
            for (Object object : objects) {
                System.out.print(object);
                System.out.print(",");
            }
        }

    }

    @Test
    void testORM(){
        Class clazz = Tbond.class;
        Constructor[] constructors = clazz.getConstructors();
        Constructor constructor1 = null;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(StringUtil.camelToUnderline(declaredField.getName(), 1));
        }
        Class[] parameterTypes = null;

        for (Constructor constructor : constructors) {
            if(constructor.getParameterCount() != declaredFields.length){
                System.out.println("不是全参构造函数略过");
                continue;
            }
            constructor1 = constructor;
            break;
        }

        SqlFieldsQuery sqlFieldsQuery = new SqlFieldsQuery("SELECT security_id, bond_interest from tbond");
        List<List<?>> all = client.query(sqlFieldsQuery).getAll();
        for (List<?> objects : all) {
            Object[] objects1 = objects.toArray();

            try {
                Object o = constructor1.newInstance(objects1);
                Tbond tbond = (Tbond) o;
                System.out.println(tbond);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

    }

    @Autowired
    ORMUtil ormUtil;
    @Test
    void testOrm2(){
        String className = "zhuky.clear.entity.Tshareholder";
        String sql = ormUtil.getSql(className);
        List<Object> objects = ormUtil.queryAll("select " + sql + " from tshareholder", className);
        for (Object object : objects) {
            System.out.println(object);
        }
    }


    @Test
    void testOrm3(){
        Object[] args = {1};
        List<Object> tshareholder = ormUtil.querySingleTable("Tproduct", "product_id = ?", args);
        for (Object o : tshareholder) {
            System.out.println(o);
        }
    }

}
