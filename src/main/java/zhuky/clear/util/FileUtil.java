package zhuky.clear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import zhuky.clear.exception.BusinessErrorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                if(tempString.trim().length() == 0) {
                    continue;
                }
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

    /**
     * 获取类路径下的资源文件
     * @param path
     * @return
     * @throws IOException
     */
    public static File getClassPathFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getFile();
    }

    /**
     * 获取工程所在根目录地址
     * @return
     */
    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }


    public List<File> getAllFiles(String path){
        ClassPathResource resource = new ClassPathResource(path);
        List<File> files = new ArrayList<>();
        try {
            File file = resource.getFile();
            if(file.isDirectory()){
                folderMethod(file, files);
            }else {
                files.add(file);
            }
        } catch (IOException e) {
            logger.error("获取文件列表出错，{}", e.getMessage());
            throw new BusinessErrorException("-1", "获取文件列表出错，"+e.getMessage());
        }
        return files;
    }

    public void folderMethod(File file, List<File> files){
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (null != files) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        folderMethod(file2, files);
                    } else {
                        files.add(file2);
                    }
                }
            }
        }
    }

    public File getSqlFile(String name){
        List<File> allFiles = getAllFiles("db/schema");
        File file = null;
        for (File file1 : allFiles) {
            if(file1.getName().endsWith(name + ".sql")){
                file = file1;
            }
        }
        return file;
    }

}
