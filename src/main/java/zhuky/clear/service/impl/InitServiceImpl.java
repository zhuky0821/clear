package zhuky.clear.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuky.clear.service.InitService;
import zhuky.clear.util.SqlUtil;

@Service
public class InitServiceImpl implements InitService {
    private static final Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);

    @Autowired
    private SqlUtil sqlUtil1;

    @Override
    public void initSchema() {
        logger.info("处理表结构重建开始");

        sqlUtil1.execSqlFile("db/schema");

        logger.info("处理表结构重建结束");
    }

    @Override
    public void initData() {
        logger.info("处理表数据开始");

        sqlUtil1.execSqlFile("db/data.sql");

        logger.info("处理表数据结束");
    }


}
