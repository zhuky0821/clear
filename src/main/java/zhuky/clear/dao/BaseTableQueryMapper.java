package zhuky.clear.dao;

import zhuky.clear.entity.Tfilecolumnconfig;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;

import java.util.List;

public interface BaseTableQueryMapper {
    List<Tshareholder> getProductUseShareholder(int productId);

    Tsecurity getSecurity(String securityCode, int mktId);

    Tsecurity getSecurityById(int securityId);

    List<Tfilecolumnconfig> getFileColumnConfigs(String tableName);
}
