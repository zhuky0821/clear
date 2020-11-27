package zhuky.clear.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.dao.InterfaceTableQueryMapper;
import zhuky.clear.entity.TTmpCurrents;
import zhuky.clear.entity.Tjsmx;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.service.Identify;

import java.util.List;

public class IdentifyJsmx implements Identify {

    @Autowired
    InterfaceTableQueryMapper interfaceTableQueryMapper;
    @Autowired
    BaseTableQueryMapper baseTableQueryMapper;

    @Override
    public List<TTmpCurrents> identifyFile(int productId) {

        List<Tshareholder> productUseShareholder = baseTableQueryMapper.getProductUseShareholder("," + productId + ",");
        for (Tshareholder tshareholder : productUseShareholder) {
            if(tshareholder.getMktId() != 1) continue;

            List<Tjsmx> tjsmxes = interfaceTableQueryMapper.jsmx(tshareholder.getShareholderId());
            for (Tjsmx tjsmx : tjsmxes) {
                int businFlag = 0;
                if(tjsmx.getYwlx().trim().equals("001") && tjsmx.getYwlx().trim().equals("B")){
                    //股票买入
                    businFlag = 2000;
                }else if(tjsmx.getYwlx().trim().equals("001") && tjsmx.getYwlx().trim().equals("S")){
                    //股票卖出
                    businFlag = 2001;
                }else if(tjsmx.getYwlx().trim().equals("003") && tjsmx.getYwlx().trim().equals("B")){
                    //债券买入
                    businFlag = 2500;
                }else if(tjsmx.getYwlx().trim().equals("003") && tjsmx.getYwlx().trim().equals("S")){
                    //债券卖出
                    businFlag = 2501;
                }else if(tjsmx.getYwlx().trim().equals("023") && tjsmx.getYwlx().trim().equals("B")){
                    //融资回购
                    businFlag = 2504;
                }else if(tjsmx.getYwlx().trim().equals("023") && tjsmx.getYwlx().trim().equals("S")){
                    //融券回购
                    businFlag = 2506;
                }else {
                    continue;
                }

                Tsecurity tsecurity = baseTableQueryMapper.getSecurity(tjsmx.getZqdm1(), 1);
                if(tsecurity == null) continue;

                TTmpCurrents tmpCurrents = new TTmpCurrents();
                StringBuilder posStr = new StringBuilder();
                posStr.append("tjsmx_").append(productId).append("_").append(tjsmx.getRecordId());
                tmpCurrents.setPosStr(posStr.toString());
                tmpCurrents.setProductId(productId);
                tmpCurrents.setUnitId(0);
                tmpCurrents.setCombiId(0);
                tmpCurrents.setSecurityId(tsecurity.getSecurityId());


            }
        }

        return null;
    }
}
