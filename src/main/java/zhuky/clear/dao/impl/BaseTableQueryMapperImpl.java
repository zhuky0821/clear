package zhuky.clear.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.entity.TFileColumnConfig;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.util.ORMUtil;

import java.util.List;

@Component
public class BaseTableQueryMapperImpl implements BaseTableQueryMapper {

    @Autowired
    ORMUtil ormUtil;

    @Override
    public List<Tshareholder> getProductUseShareholder(int productId) {
        List<Tshareholder> all = ormUtil.querySingleTable("Tshareholder", "instr(','||USE_PRODUCT_LIST||',', ','|| ? ||',')", productId);
        return all;
    }

    @Override
    public Tsecurity getSecurity(String securityCode, int mktId) {
        List<Tsecurity> all = ormUtil.querySingleTable("Tsecurity", "security_code = ? and mkt_id = ?", securityCode, mktId);
        return all.get(0);
    }

    @Override
    public Tsecurity getSecurityById(int securityId) {
        List<Tsecurity> all = ormUtil.querySingleTable("Tsecurity", "security_id = ?", securityId);
        return all.get(0);
    }

    @Override
    public List<TFileColumnConfig> getFileColumnConfigs(String tableName) {
        List<TFileColumnConfig> all = ormUtil.querySingleTable("TFileColumnConfig", "table_name = ? order by begin_pos", tableName);
        return all;
    }
}
