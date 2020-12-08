package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tcombistock {
    private int productId;
    private int unitId;
    private int combiId;
    private int securityId;
    private int investType;
    private int positionType;
    private String bindSeat;
    private String shareholderId;
    private BigDecimal beginQty;
    private BigDecimal currentQty;
    private BigDecimal beginCost;
    private BigDecimal currentCost;
    private BigDecimal beginProfit;
    private BigDecimal currentProfit;
    private BigDecimal beginInterestCost;
    private BigDecimal beginInterestProfit;
    private BigDecimal currentInterestCost;
    private BigDecimal currentInterestProfit;
}
