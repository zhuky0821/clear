package zhuky.clear.dao.impl;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.ClearContext;
import zhuky.clear.dao.CommonQueryMapper;

import java.util.List;

@Component
public class CommonQueryMapperImpl implements CommonQueryMapper {
    private static final Logger logger = LoggerFactory.getLogger(CommonQueryMapperImpl.class);
    @Autowired
    IgniteCache igniteCache;

    @Override
    public List<List<?>> commonQuery(String sql, Object... args) {
        logger.trace("执行通用查询sql{}，参数：{}", sql, args);
        return igniteCache.query(new SqlFieldsQuery(sql).setArgs(args)).getAll();
    }
}
