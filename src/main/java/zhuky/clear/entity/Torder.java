package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Torder {
    private int productId;
    private int unitId;
    private int combiId;
    private int reportSerialId;
    private int securityId;
    private String shareholderId;
    private int investType;
    private int sideCode;
}
