package zhuky.clear.dao;

import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;

import java.util.List;

public interface BaseTableQueryMapper {
    List<Tshareholder> getProductUseShareholder(String productIdStr);

    Tsecurity getSecurity(String securityCode, int mktId);

}
