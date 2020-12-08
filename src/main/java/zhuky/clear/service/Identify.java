package zhuky.clear.service;

import zhuky.clear.entity.Ttmpcurrents;

import java.util.List;

public interface Identify {
    List<Ttmpcurrents> identifyFile(int productId, int businessDate);
}
