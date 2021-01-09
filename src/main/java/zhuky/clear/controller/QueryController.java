package zhuky.clear.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.dao.CommonDbMapper;
import zhuky.clear.entity.Tsecurity;

@RestController
@RequestMapping("/query")
@Api(value = "系统查询接口")
public class QueryController {

    @Autowired
    BaseTableQueryMapper baseTableQueryMapper;



    @GetMapping("/security")
    @ApiOperation(value = "证券资料查询")
    public Tsecurity getTsecurity(String securityCode, int mktId){
        return baseTableQueryMapper.getSecurity(securityCode, mktId);
    }

    @GetMapping("/test")
    public void testQuery(){
        for (int i = 0; i < 100000; i++) {
            baseTableQueryMapper.getSecurity("600001", 1);
        }
    }
}
