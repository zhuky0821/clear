package zhuky.clear.dao;

import java.util.List;

public interface CommonQueryMapper {
    List<List<?>> commonQuery(String sql, Object... args);
}
