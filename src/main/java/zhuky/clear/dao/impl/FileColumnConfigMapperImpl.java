package zhuky.clear.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import zhuky.clear.entity.TFileColumnConfig;
import zhuky.clear.util.ORMUtil;

import java.util.ArrayList;
import java.util.List;

public class FileColumnConfigMapperImpl implements zhuky.clear.dao.FileColumnConfigMapper {

    @Autowired
    private ORMUtil ormUtil;

    @Override
    public List<TFileColumnConfig> getFileColumnConfigs(String tableName) {
        List<TFileColumnConfig> all = ormUtil.querySingleTable("TFileColumnConfig", "table_name = ? order by begin_pos", tableName);
        return all;
    }
}
