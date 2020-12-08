package zhuky.clear;

import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.entity.Tbond;
import zhuky.clear.entity.Tproduct;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.util.ORMUtil;
import zhuky.clear.util.StringUtil;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QueryTest {
    private static final Logger logger = LoggerFactory.getLogger(QueryTest.class);

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
        List<Tproduct> tshareholder = ormUtil.querySingleTable("Tproduct", "product_id = ?", 1);
        for (Object o : tshareholder) {
            System.out.println(o);
        }
    }

    @Test
    void  testOrm4(){
        Class clazz = Tbond.class;
        try {
            SqlFieldsQuery sqlFieldsQuery = new SqlFieldsQuery("SELECT security_id, bond_interest from tbond");
            List<List<?>> all = client.query(sqlFieldsQuery).getAll();

            Constructor constructor = clazz.getConstructor();

            List<Object> os = new ArrayList<>();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (List<?> objects : all) {
                Object o = constructor.newInstance();
                for(int i=0; i<declaredFields.length; i++){
                    Field declaredField = declaredFields[i];
                    declaredField.setAccessible(true);
                    declaredField.set(o, objects.get(i));
                }
                os.add(o);
            }

            System.out.println(os);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 100000次查询大概耗时114s
     * 平均每次查询（包含反射出对象）耗时1.14ms
     */
    @Test
    void testOrmxn(){
        logger.info("测试ORM性能开始");
        for (int i = 0; i < 10000; i++) {

            Tsecurity security = baseTableQueryMapper.getSecurity("600001", 1);
            if(i % 10000 == 0 && i > 0){
                logger.info(security.toString());
            }
        }
        //baseTableQueryMapper.getFileColumnConfigs("tjsmx");

        logger.info("测试ORM性能结束");
    }

}
