package zhuky.clear.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zhuky.clear.entity.Tflowtask;
import zhuky.clear.entity.Ttmpcurrents;
import zhuky.clear.service.FileImport;
import zhuky.clear.service.Identify;
import zhuky.clear.util.SpringContextUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ClearFlow implements Callable<Map<Integer, String>> {
    private static final Logger logger = LoggerFactory.getLogger(ClearFlow.class);

    private Tflowtask tflowtask;

    private FileImport fileImport;
    private Identify identify;

    public ClearFlow(Tflowtask tflowtask) {
        this.tflowtask = tflowtask;
        this.identify = SpringContextUtil.getBean(Identify.class);
        this.fileImport = SpringContextUtil.getBean(FileImport.class);
//        this.clearContext = SpringContextUtil.getBean(ClearContext.class);
    }


    @Override
    public Map<Integer, String> call() throws Exception {
        logger.info("执行流程任务{}开始", tflowtask);
        Map<Integer, String> result = new HashMap<>();

        if(tflowtask.getFlowId() == 1){
            //文件導入
            fileImport.importFile("d:\\jsmx.csv", "tjsmx");
            logger.info("文件導入成功");
        }else {
            List<Ttmpcurrents> ttmpcurrents = identify.identifyFile(tflowtask.getProductId(), tflowtask.getBusinessDate());

            logger.info("待入账流水条数{}", ttmpcurrents.size());
        }

        result.put(tflowtask.getProductId(), "");
        return result;
    }
}
