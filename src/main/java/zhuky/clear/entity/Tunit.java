package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tunit {
    private int productId;
    private int unitId;
    private String unitCode;
    private String unitName;
    private int defaultFlag;
}
