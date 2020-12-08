package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tcombi {
    private int productId;
    private int unitId;
    private int combiId;
    private int defaultFlag;
    private String combiCode;
    private String combiName;
}
