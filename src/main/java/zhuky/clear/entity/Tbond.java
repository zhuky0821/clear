package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tbond {
    private int securityId;
    private BigDecimal bondInterest;
}
