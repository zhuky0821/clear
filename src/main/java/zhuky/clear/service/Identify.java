package zhuky.clear.service;

import zhuky.clear.entity.TTmpCurrents;

import java.util.List;

public interface Identify {
    List<TTmpCurrents> identifyFile(int productId);
}
