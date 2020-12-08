package zhuky.clear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import zhuky.clear.exception.BusinessErrorException;

import java.io.*;

@Component
public class FileUtil {
    Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public String readFileByLines(File file) {
        StringBuffer str = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if(tempString.trim().length() == 0) continue;
                str = str.append(tempString + "\r\n");
            }
            reader.close();
        } catch (IOException e) {
            logger.error("文件{}读取失败，错误信息：{}",file.toString(),e.getMessage());
            throw new BusinessErrorException("1003", "文件"+file.toString()+"读取失败，错误信息：" + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return str.toString();
    }

    public File getClassPathFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getFile();
    }
}
