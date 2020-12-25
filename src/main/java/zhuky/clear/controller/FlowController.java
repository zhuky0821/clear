package zhuky.clear.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhuky.clear.config.ClearContext;
import zhuky.clear.dao.CommonDbMapper;
import zhuky.clear.entity.Tflowtask;
import zhuky.clear.exception.JsonResult;
import zhuky.clear.service.FileImport;
import zhuky.clear.service.impl.ClearFlow;
import zhuky.clear.util.SpringContextUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/flow")
@Api(value = "清算流程接口")
public class FlowController {
    Logger logger = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private ExecutorService executorService;

    @Autowired
    FileImport fileImport;
    @Autowired
    CommonDbMapper commonDbMapper;

    @GetMapping(path = "/process")
    public JsonResult exec(){
        logger.info("文件导入开始");
        fileImport.importFile("d:\\jsmx.csv", "tjsmx");
        logger.info("文件导入结束");

        List<List<?>> products = commonDbMapper.commonQuery("select product_id from tproduct");
        List<ClearFlow> tasks = new ArrayList<>();
        for (List<?> product : products) {
            Tflowtask task = new Tflowtask(2, (Integer) product.get(0), 0);
            tasks.add(new ClearFlow(task));
        }
        try {
            logger.info("开始并发调用清算处理");
            List<Future<Map<Integer, String>>> futures1 = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("清算处理调用结束");
        return new JsonResult("0", "清算处理成功！");
    }

    @ApiOperation(value = "文件導入")
    @RequestMapping("/import")

    public void importFile(){
        logger.info("执行文件導入开始");
        FileImport fileImport = SpringContextUtil.getBean(FileImport.class);
        fileImport.importFile("d:\\jsmx.csv", "tjsmx");
        logger.info("文件導入成功");

    }

    @ApiOperation(value = "待入帳生成")
    @RequestMapping("/create")
    public void create(){
        logger.info("待入賬流水生成开始");
        List<ClearFlow> tasks = new ArrayList<>();
        for(int i=1; i<6; i++){
            Tflowtask tflowtask = new Tflowtask();
            tflowtask.setProductId(i);
            tasks.add(new ClearFlow(tflowtask));
        }

        try {
            List<Future<Map<Integer, String>>> futures1 = executorService.invokeAll(tasks);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
