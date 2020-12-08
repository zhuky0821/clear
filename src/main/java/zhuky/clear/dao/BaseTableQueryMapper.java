package zhuky.clear.dao;

import zhuky.clear.entity.TFileColumnConfig;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;

import java.util.List;

public interface BaseTableQueryMapper {
    List<Tshareholder> getProductUseShareholder(int productId);

    Tsecurity getSecurity(String securityCode, int mktId);

    Tsecurity getSecurityById(int securityId);

    List<TFileColumnConfig> getFileColumnConfigs(String tableName);
}
