package zhuky.clear.util;

import com.linuxense.javadbf.DBFHeader;
import com.linuxense.javadbf.DBFReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DbfUtil {
    private static final Logger logger = LoggerFactory.getLogger(DbfUtil.class);

    public void dbf2csv(String fileName) throws FileNotFoundException {
        logger.info("Dbf文件：{}，转换成csv开始", fileName);

        try {
            DBFReader dbfReader = new DBFReader(new FileInputStream(fileName));


        } catch (FileNotFoundException e) {
            logger.error("文件{}找不到", fileName);
            throw e;
        }

        logger.info("Dbf文件：{}，转换成csv结束", fileName);
    }
}
