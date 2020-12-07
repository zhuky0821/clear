package zhuky.clear.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import zhuky.clear.dao.InterfaceTableQueryMapper;
import zhuky.clear.entity.Tjsmx;
import zhuky.clear.util.ORMUtil;

import java.util.ArrayList;
import java.util.List;

public class InterfaceTableQueryMapperImpl implements InterfaceTableQueryMapper {

    @Autowired
    private ORMUtil ormUtil;

    @Override
    public List<Tjsmx> jsmx(String shareholderId) {
        List<Tjsmx> jsmxs = new ArrayList<>();
        List<Object> all = ormUtil.querySingleTable("Tjsmx", "zqzh = ?", shareholderId);
        for (Object o : all) {
            Tjsmx jsmx = (Tjsmx) o;
            jsmxs.add(jsmx);
        }
        return jsmxs;
    }
}
