package zhuky.clear.entity.ignite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TshareholderValue {
    private String shareholder_name;
    private String bind_seat;
    private int combi_id;
    private String use_product_list;
}
