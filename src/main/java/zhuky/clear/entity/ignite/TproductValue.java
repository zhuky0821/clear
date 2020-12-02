package zhuky.clear.entity.ignite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TproductValue {
    private String product_code;
    private String product_name;
    private BigDecimal product_share;
}
