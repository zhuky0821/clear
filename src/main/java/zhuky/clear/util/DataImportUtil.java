package zhuky.clear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhuky.clear.config.ClearContext;

import java.util.concurrent.*;

@Component
public class DataImportUtil implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(DataImportUtil.class);


    @Autowired
    ClearContext clearContext;

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
                logger.info("消费者消费");
                result = clearContext.getBlockingQueue().poll(2, TimeUnit.SECONDS);
                if (null!=result){

                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
