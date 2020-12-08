package zhuky.clear.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhuky.clear.service.InitService;

@RestController
@RequestMapping("/init")
@Api(value = "系统初始化接口")
public class InitController {
    Logger logger = LoggerFactory.getLogger(InitController.class);

    @Autowired
    private InitService initService;

    @GetMapping("/schema")
    @ApiOperation(value = "初始化数据库表结构")
    public void SchemaInitial() {
        initService.initSchema();
    }

    @GetMapping("/data")
    @ApiOperation(value = "初始化数据库基础数据")
    public void DataInitial() {
        initService.initData();
    }
}
