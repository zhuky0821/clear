package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tmarket {
    private int tradedayTypeId;
    private int mktId;
    private int businessDate;
    private int nextBusinessDate;
    private int preBusinessDate;
}
