package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tjournal {
    private String serialId;
    private int productId;
    private int unitId;
    private int combiId;
    private int securityId;
    private int businFlag;
    private int investType;
    private int positionType;
    private String shareholderId;
    private String bindSeat;
    private int subjectId;
    private BigDecimal subjectOccurAmt;
    private BigDecimal occurAmt;
    private BigDecimal occurQty;
    private String remark;
    private int reportSerialId;
    private int securityType;
    private int mktId;
    private String dealId;
    private BigDecimal dealPrice;
    private BigDecimal dealQty;
    private BigDecimal dealAmt;
    private BigDecimal yj;
    private BigDecimal yhs;
    private BigDecimal ghf;
    private BigDecimal jsf;
    private BigDecimal qtfy;
    private BigDecimal zgf;
    private BigDecimal jsfwf;
    private BigDecimal jgf;
}
