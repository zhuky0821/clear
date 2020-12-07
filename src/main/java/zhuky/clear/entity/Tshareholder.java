package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tshareholder {
    private String shareholderId;
    private int mktId;
    private String shareholderName;
    private String bindSeat;
    private int combiId;
    private String useProductList;
}
