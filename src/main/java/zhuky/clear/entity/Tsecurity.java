package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tsecurity {
    private int securityId;
    private String securityCode;
    private int mktId;
    private String securityName;
    private BigDecimal lastPrice;
    private int securityType;
    private int assetType;
}
