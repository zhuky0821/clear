package zhuky.clear.dao;

import java.util.List;

public interface CommonDbMapper {
    List<List<?>> commonQuery(String sql, Object... args);

    void update(String sql, Object... args);

    void insert(Object data);

    void insertBatch(List<?> data);
}
