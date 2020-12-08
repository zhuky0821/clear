package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ttradedate {
    private int tradedayTypeId;
    private int sysDate;
    private int tradedayFlag;
    private int settledayFlag;
}
