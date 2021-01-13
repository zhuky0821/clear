package zhuky.clear.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zhuky.clear.dao.CommonDbMapper;
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
    CommonDbMapper commonDbMapper;

    public ClearFlow(Tflowtask tflowtask) {
        this.tflowtask = tflowtask;
        this.identify = SpringContextUtil.getBean(Identify.class);
        this.fileImport = SpringContextUtil.getBean(FileImport.class);
        this.commonDbMapper = SpringContextUtil.getBean(CommonDbMapper.class);
    }


    @Override
    public Map<Integer, String> call() throws Exception {
        logger.info("执行流程任务{}开始", tflowtask);
        Map<Integer, String> result = new HashMap<>();

        if(tflowtask.getFlowId() == 2){
            List<Ttmpcurrents> ttmpcurrents = identify.identifyFile(tflowtask.getProductId(), tflowtask.getBusinessDate());

            logger.info("待入账流水条数{}", ttmpcurrents.size());

            commonDbMapper.insertBatch(ttmpcurrents);

        }

        result.put(tflowtask.getProductId(), "");
        return result;
    }
}
