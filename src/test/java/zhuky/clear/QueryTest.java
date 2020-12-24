package zhuky.clear;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.dao.CommonDbMapper;
import zhuky.clear.dao.impl.CommonDbMapperImpl;
import zhuky.clear.entity.Tproduct;
import zhuky.clear.entity.Treporatio;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.util.FileUtil;
import zhuky.clear.util.ORMUtil;

import java.io.File;
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
    ORMUtil ormUtil;
    @Test
    void testOrm2(){
        Class clazz = Tshareholder.class;
        String sql = ormUtil.getSql(clazz);
        List<Object> objects = ormUtil.queryAll(clazz,"select " + sql + " from tshareholder");
        for (Object object : objects) {
            System.out.println(object);
        }
    }


    @Test
    void testOrm3(){
        List<Tproduct> tshareholder = ormUtil.querySingleTable(Tproduct.class, "product_id = ?", 1);
        for (Object o : tshareholder) {
            System.out.println(o);
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

    @Autowired
    CommonDbMapper commonQueryMapper;

    @Test
    void testCommonQuery(){
        List<List<?>> lists = commonQueryMapper.commonQuery("select * from tproduct");
        for (List<?> list : lists) {
            System.out.println(list);
        }
    }

    @Autowired
    FileUtil fileUtil;
    @Test
    void testFile(){
        List<File> allFiles = fileUtil.getAllFiles("db");
        System.out.println(allFiles);
    }

    @Test
    void testInsert(){
        Treporatio treporatio = new Treporatio();
        treporatio.setSecurityId(1);
        treporatio.setRepoParamType(2);
        treporatio.setRepoParamValue(3);
        List<Treporatio> treporatios = new ArrayList<>();
        treporatios.add(treporatio);
        CommonDbMapperImpl dbMapper = new CommonDbMapperImpl();
        dbMapper.getAllSql(treporatios);
    }
}
