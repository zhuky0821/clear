package zhuky.clear.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
@Api(value = "系统查询接口")
public class QueryController {

//    @Autowired
//    BaseTableQueryMapper baseTableQueryMapper;
//
//    @GetMapping("/security")
//    @ApiOperation(value = "证券资料查询")
//    public Tsecurity getTsecurity(String securityCode, int mktId){
//        return baseTableQueryMapper.getSecurity(securityCode, mktId);
//    }
}
