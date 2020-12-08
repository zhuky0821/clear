package zhuky.clear;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.entity.Tbond;
import zhuky.clear.util.CodeGeneratorUtil;

import java.io.File;

@SpringBootTest
public class CodeGeneratorTool {

    @Autowired
    CodeGeneratorUtil util;
    @Test
    void test(){
        util.createTableFile();
    }
}
