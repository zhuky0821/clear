package zhuky.clear.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.dao.InterfaceTableQueryMapper;
import zhuky.clear.entity.Ttmpcurrents;
import zhuky.clear.entity.Tjsmx;
import zhuky.clear.entity.Tsecurity;
import zhuky.clear.entity.Tshareholder;
import zhuky.clear.service.Identify;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class IdentifyImpl implements Identify {

    @Autowired
    InterfaceTableQueryMapper interfaceTableQueryMapper;
    @Autowired
    BaseTableQueryMapper baseTableQueryMapper;

    @Override
    public List<Ttmpcurrents> identifyFile(int productId, int businessDate) {

        List<Tshareholder> productUseShareholder = baseTableQueryMapper.getProductUseShareholder(productId);

        List<Ttmpcurrents> tmpCurrentsList = new ArrayList<>();

        for (Tshareholder tshareholder : productUseShareholder) {
            if(tshareholder.getMktId() != 1) {
                continue;
            }

            List<Tjsmx> tjsmxes = interfaceTableQueryMapper.jsmx(tshareholder.getShareholderId());
            for (Tjsmx tjsmx : tjsmxes) {
                int businFlag;
                if("001".equals(tjsmx.getYwlx().trim()) && "B".equals(tjsmx.getMmbz().trim())){
                    //股票买入
                    businFlag = 2000;
                }else if("001".equals(tjsmx.getYwlx().trim()) && "S".equals(tjsmx.getMmbz().trim())){
                    //股票卖出
                    businFlag = 2001;
                }else if("003".equals(tjsmx.getYwlx().trim()) && "B".equals(tjsmx.getMmbz().trim())){
                    //债券买入
                    businFlag = 2500;
                }else if("003".equals(tjsmx.getYwlx().trim()) && "S".equals(tjsmx.getMmbz().trim())){
                    //债券卖出
                    businFlag = 2501;
                }else if("023".equals(tjsmx.getYwlx().trim()) && "B".equals(tjsmx.getMmbz().trim())){
                    //融资回购
                    businFlag = 2504;
                }else if("023".equals(tjsmx.getYwlx().trim()) && "S".equals(tjsmx.getMmbz().trim())){
                    //融券回购
                    businFlag = 2506;
                }else {
                    continue;
                }

                Tsecurity tsecurity = baseTableQueryMapper.getSecurity(tjsmx.getZqdm1(), 1);
                if(tsecurity == null) {
                    continue;
                }

                Ttmpcurrents tmpCurrents = new Ttmpcurrents();
                String posStr = "tjsmx_" + productId + "_" + tjsmx.getRecordId();
                tmpCurrents.setPosStr(posStr);
                tmpCurrents.setProductId(productId);
                tmpCurrents.setUnitId(0);
                tmpCurrents.setCombiId(0);
                tmpCurrents.setSecurityId(tsecurity.getSecurityId());
                tmpCurrents.setInvestType(0);
                tmpCurrents.setPositionType(1);
                tmpCurrents.setShareholderId(tjsmx.getZqzh());
                tmpCurrents.setBindSeat(tshareholder.getBindSeat());
                tmpCurrents.setBusinFlag(businFlag);
                tmpCurrents.setSubjectId(0);
                tmpCurrents.setSubjectOccurAmt(new BigDecimal(0));
                tmpCurrents.setOccurAmt(tjsmx.getSjsf());
                tmpCurrents.setOccurQty(("GZ".equals(tjsmx.getZqlb().trim())) ? tjsmx.getSl().divide(new BigDecimal(100), 0, RoundingMode.FLOOR) : tjsmx.getSl() );
                tmpCurrents.setRemark(tjsmx.getBz());
                tmpCurrents.setReportSerialId(Integer.parseInt(tjsmx.getSqbh()));
                tmpCurrents.setSecurityType(tsecurity.getSecurityType());
                tmpCurrents.setMktId(tsecurity.getMktId());
                tmpCurrents.setDealId(tjsmx.getCjbh());
                tmpCurrents.setDealPrice(tjsmx.getJg1());
                tmpCurrents.setDealQty(tmpCurrents.getOccurQty().abs());
                tmpCurrents.setDealAmt(tjsmx.getQsje());
                tmpCurrents.setYj(new BigDecimal(0));
                tmpCurrents.setYhs(tjsmx.getYhs());
                tmpCurrents.setGhf(tjsmx.getGhf());
                tmpCurrents.setJsf(tjsmx.getJsf());
                tmpCurrents.setQtfy(tjsmx.getSxf());
                tmpCurrents.setZgf(tjsmx.getZgf());
                tmpCurrents.setJsfwf(new BigDecimal(0));
                tmpCurrents.setJgf(new BigDecimal(0));
                tmpCurrents.setReceivableInterestBal(new BigDecimal(0));
                tmpCurrents.setHgInterest(new BigDecimal(0));
                tmpCurrents.setTradeDate(businessDate);
                tmpCurrents.setActualBuybackDate(0);
                tmpCurrents.setLegalBuybackDate(0);

                tmpCurrentsList.add(tmpCurrents);
            }
        }

        return tmpCurrentsList;
    }
}
