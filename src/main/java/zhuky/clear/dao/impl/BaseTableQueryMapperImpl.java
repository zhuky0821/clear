package zhuky.clear.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.ClearContext;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.entity.Tfilecolumnconfig;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.util.ORMUtil;

import java.util.List;
import java.util.Map;

@Component
public class BaseTableQueryMapperImpl implements BaseTableQueryMapper {

    @Autowired
    ORMUtil ormUtil;
    @Autowired
    ClearContext clearContext;

    @Override
    public List<Tshareholder> getProductUseShareholder(int productId) {
        List<Tshareholder> all = ormUtil.querySingleTable("Tshareholder", "instr(','||USE_PRODUCT_LIST||',', ','|| ? ||',')", productId);
        return all;
    }

    @Override
    public Tsecurity getSecurity(String securityCode, int mktId) {
        Map<String, Tsecurity> tsecurityCodeMktCache = clearContext.getTsecurityCodeMktCache();
        Tsecurity tsecurity = tsecurityCodeMktCache.get(securityCode + "_" + mktId);
        if(tsecurity == null){
            List<Tsecurity> all = ormUtil.querySingleTable("Tsecurity", "security_code = ? and mkt_id = ?", securityCode, mktId);
            tsecurity = all.get(0);
            tsecurityCodeMktCache.put(securityCode + "_" + mktId, tsecurity);
        }
        return tsecurity;
    }

    @Override
    public Tsecurity getSecurityById(int securityId) {
        List<Tsecurity> all = ormUtil.querySingleTable("Tsecurity", "security_id = ?", securityId);
        return all.get(0);
    }

    @Override
    public List<Tfilecolumnconfig> getFileColumnConfigs(String tableName) {
        List<Tfilecolumnconfig> all = ormUtil.querySingleTable("TFileColumnConfig", "table_name = ? order by begin_pos", tableName);
        return all;
    }
}
