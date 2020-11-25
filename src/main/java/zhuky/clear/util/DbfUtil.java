package zhuky.clear.util;

import com.linuxense.javadbf.DBFReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zhuky.clear.dao.FileColumnConfigMapper;
import zhuky.clear.entity.TFileColumnConfig;

import java.io.*;
import java.util.List;

public class DbfUtil {
    private static final Logger logger = LoggerFactory.getLogger(DbfUtil.class);

    @Autowired
    FileColumnConfigMapper fileColumnConfigMapper;

    public void dbf2csv(String fileName, String tableName) throws FileNotFoundException {
        logger.info("Dbf文件：{}，转换成csv开始", fileName);

        DataInputStream dataInputStream = null;
        DBFReader reader = null;
        try {
            reader = new DBFReader(new FileInputStream(fileName));
            int fieldCount = reader.getFieldCount();

            dataInputStream = new DataInputStream(new FileInputStream(fileName));
            byte[] byte2 = new byte[2];
            dataInputStream.read(byte2);
            dataInputStream.read(byte2);
            /**
             * 4-7字节为文件中的记录条数。
             */
            int rowCount = readLittleEndianInt(dataInputStream);
            dataInputStream.read(byte2);
            /**
             * 10-11字节，一条记录中的字节长度。值应该是一条记录中所有字段的长度之和再加上1个字节，这额外一个字节是控制位，表示该记录是否被删除。
             */

            int allFieldLength = readLittleEndianShort(dataInputStream);
            byte[] byte20 = new byte[20];
            dataInputStream.read(byte20);

            byte[] header = new byte[32];


            //获取导入配置
            List<TFileColumnConfig> fileColumnConfigs = fileColumnConfigMapper.getFileColumnConfigs(tableName);



        } catch (FileNotFoundException e) {
            logger.error("文件{}找不到", fileName);
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                reader.close();
            }
        }

        logger.info("Dbf文件：{}，转换成csv结束", fileName);
    }

    public static int readLittleEndianInt(DataInput in) throws IOException {
        int bigEndian = 0;
        for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
            bigEndian |= (in.readUnsignedByte() & 0xff) << shiftBy;
        }
        return bigEndian;
    }

    public static short readLittleEndianShort(DataInput in) throws IOException {
        int low = in.readUnsignedByte() & 0xff;
        int high = in.readUnsignedByte();
        return (short) (high << 8 | low);
    }

}
