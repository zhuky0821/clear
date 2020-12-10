package zhuky.clear.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhuky.clear.config.ClearContext;
import zhuky.clear.entity.Tflowtask;
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

    @ApiOperation(value = "执行流程任务")
    @GetMapping("/exec")
    public void exec(){
        logger.info("执行流程任务开始");
        ExecutorService executorService = clearContext.getExecutorService();

        List<ClearFlow> tasks = new ArrayList<>();
        for(int i=1; i<3; i++){
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
