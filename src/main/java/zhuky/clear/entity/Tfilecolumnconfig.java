package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tfilecolumnconfig {
    private String tableName;
    private String columnName;
    private int beginPos;
    private int endPos;
}
