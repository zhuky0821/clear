package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tproduct {
    private int product_id;
    private String productCode;
    private String productName;
    private BigDecimal productShare;
}
