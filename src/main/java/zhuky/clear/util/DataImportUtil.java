package zhuky.clear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.ClearContext;
import zhuky.clear.dao.CommonDbMapper;

import java.util.concurrent.*;

public class DataImportUtil implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(DataImportUtil.class);

    ClearContext clearContext;
    CommonDbMapper commonDbMapper;

    public DataImportUtil(){
        this.clearContext = SpringContextUtil.getBean(ClearContext.class);
        this.commonDbMapper = SpringContextUtil.getBean(CommonDbMapper.class);
    }

    public <E> void insert(E e) throws InterruptedException {
        boolean success = clearContext.getBlockingQueue().offer(e, 2, TimeUnit.SECONDS);
        if (!success){
            logger.error("插入队列"+ e + "失败"+"资源队列大小= " + clearContext.getBlockingQueue().size());
        }
    }

    @Override
    public void run() {
        Object result = null;
        while (clearContext.getFLAG()){
            try {
                //logger.info("消费者消费");
                result = clearContext.getBlockingQueue().poll(2, TimeUnit.SECONDS);
                if (null!=result){

                }
                else{
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
