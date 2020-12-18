package zhuky.clear.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhuky.clear.config.ClearContext;
import zhuky.clear.entity.Tflowtask;
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
    private ClearContext clearContext;

    @PostMapping(path = "/exec")
    public void exec(@RequestBody Tflowtask tflowtask){
        logger.info(tflowtask.toString());
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
        ExecutorService executorService = clearContext.getExecutorService();
        List<ClearFlow> tasks = new ArrayList<>();
        for(int i=1; i<6; i++){
            Tflowtask tflowtask = new Tflowtask();
            tflowtask.setProductId(i);
            tflowtask.setBusinessDate(20201210);
            tasks.add(new ClearFlow(tflowtask));
        }

        try {
            List<Future<Map<Integer, String>>> futures1 = executorService.invokeAll(tasks);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
