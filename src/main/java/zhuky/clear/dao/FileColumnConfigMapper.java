package zhuky.clear.dao;


import zhuky.clear.entity.TFileColumnConfig;
import java.util.List;

public interface FileColumnConfigMapper {
    List<TFileColumnConfig> getFileColumnConfigs(String tableName);
}
