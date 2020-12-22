package zhuky.clear.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuky.clear.dao.InterfaceTableQueryMapper;
import zhuky.clear.entity.Tjsmx;
import zhuky.clear.util.ORMUtil;

import java.util.List;

@Service
public class InterfaceTableQueryMapperImpl implements InterfaceTableQueryMapper {

    @Autowired
    private ORMUtil ormUtil;

    @Override
    public List<Tjsmx> jsmx(String shareholderId) {
        List<Tjsmx> all = ormUtil.querySingleTable(Tjsmx.class, "zqzh = ?", shareholderId);
        return all;
    }
}
