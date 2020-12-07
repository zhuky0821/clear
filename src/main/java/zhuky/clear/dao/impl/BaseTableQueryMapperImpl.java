package zhuky.clear.dao.impl;

import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.util.ORMUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BaseTableQueryMapperImpl implements BaseTableQueryMapper {

    @Autowired
    ORMUtil ormUtil;

    @Override
    public List<Tshareholder> getProductUseShareholder(int productId) {

        List<Tshareholder> shareholders = new ArrayList<>();

        List<Object> all = ormUtil.querySingleTable("Tshareholder", "instr(','||USE_PRODUCT_LIST||',', ','|| ? ||',')", productId);

        for (Object o : all) {
            Tshareholder shareholder = (Tshareholder) o;
            shareholders.add(shareholder);
        }

        return shareholders;
    }

    @Override
    public Tsecurity getSecurity(String securityCode, int mktId) {
        List<Object> all = ormUtil.querySingleTable("Tsecurity", "security_code = ? and mkt_id = ?", securityCode, mktId);
        Tsecurity tsecurity = (Tsecurity) all.get(0);
        return tsecurity;
    }

    @Override
    public Tsecurity getSecurityById(int securityId) {
        List<Object> all = ormUtil.querySingleTable("Tsecurity", "security_id = ?", securityId);
        Tsecurity tsecurity = (Tsecurity) all.get(0);
        return tsecurity;
    }
}
