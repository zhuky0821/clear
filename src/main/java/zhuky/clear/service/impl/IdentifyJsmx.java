package zhuky.clear.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import zhuky.clear.dao.InterfaceTableQueryMapper;
import zhuky.clear.entity.TTmpCurrents;
import zhuky.clear.service.Identify;

import java.util.List;

public class IdentifyJsmx implements Identify {

    @Autowired
    InterfaceTableQueryMapper interfaceTableQueryMapper;

    @Override
    public List<TTmpCurrents> identifyFile(int productId) {

        //

        return null;
    }
}
