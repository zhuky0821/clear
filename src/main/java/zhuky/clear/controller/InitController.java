package zhuky.clear.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ignite.IgniteCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhuky.clear.config.ClearContext;
import zhuky.clear.exception.JsonResult;
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
    public JsonResult schemaInitial() {
        initService.initSchema();
        return new JsonResult("0", "初始化数据库表结构成功！");
    }

    @GetMapping("/data")
    @ApiOperation(value = "初始化数据库基础数据")
    public JsonResult dataInitial() {
        initService.initData();
        return new JsonResult("0", "初始化基础数据成功！");
    }

    @GetMapping("/sys")
    @ApiOperation(value = "系统启动后初始化系统内存数据")
    public JsonResult sysInit(){
        initService.initSchema();
        initService.initData();
        return new JsonResult("0", "系统初始化成功！");
    }
}
