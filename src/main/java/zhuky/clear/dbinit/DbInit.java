package zhuky.clear.dbinit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbInit {
    Logger logger = LoggerFactory.getLogger(DbInit.class);

    public DbInit(){
        logger.info("执行初始化脚本开始");
        //1.执行数据库建表脚本


        //2.执行期初数据导入脚本

    }

}
