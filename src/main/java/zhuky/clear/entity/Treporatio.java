package zhuky.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treporatio {
    private int securityId;
    private int repoParamType;
    private int repoParamValue;
}
