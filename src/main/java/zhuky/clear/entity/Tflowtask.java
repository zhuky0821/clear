package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tflowtask {
    private int taskId;
    private int flowId;
    private int productId;
    private int businessDate;
    private int nextBusinessDate;
    private int taskStatus;
    private String remark;
}
