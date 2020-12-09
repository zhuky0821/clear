package zhuky.clear.util;

import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CodeGeneratorUtil {
    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    @Value("${clear.code.generator.tables}")
    private String tableNames;

    @Autowired
    IgniteClient client;

    public void createTableFile(){
        List<String> names = new ArrayList();
        if("all".equals(tableNames)){
            List<List<?>> allTables = client.query(new SqlFieldsQuery("SELECT lower(table_name) FROM SYS.TABLES")).getAll();
            for (List<?> allTable : allTables) {
                names.add((String) allTable.get(0));
            }
        }else {
            String[] strings = tableNames.split(",");
            for (String string : strings) {
                names.add(string);
            }
        }

        for (String name : names) {
            createEntity(name);
        }
    }

    void createEntity(String tableName){
        StringBuilder head = new StringBuilder();
        StringBuilder body = new StringBuilder();
        boolean existDecimal = false;

        head.append("package zhuky.clear.entity;\r\n\r\n")
                .append("import lombok.AllArgsConstructor;\r\n")
                .append("import lombok.Data;\r\n")
                .append("import lombok.NoArgsConstructor;\r\n");

        SqlFieldsQuery sqlFieldsQuery = new SqlFieldsQuery("SELECT a.column_name, a.type FROM SYS.TABLE_COLUMNS a WHERE a.table_name = upper(?) AND a.TYPE NOT IS null").setArgs(tableName);
        List<List<?>> all = client.query(sqlFieldsQuery).getAll();
        for (List<?> objects : all) {
            String columnName = StringUtil.underlineToCamel((String) objects.get(0));
            String type = (String) objects.get(1);

            switch (type){
                case "java.lang.String" :
                    body.append("    private String ").append(columnName).append(";\r\n");
                    break;
                case "java.lang.Integer":
                    body.append("    private int ").append(columnName).append(";\r\n");
                    break;
                case "java.math.BigDecimal":
                    body.append("    private BigDecimal ").append(columnName).append(";\r\n");
                    if(!existDecimal){
                        head.append("import java.math.BigDecimal;\r\n");
                        existDecimal = true;
                    }
                    break;
                default:
                    break;
            }
        }

        head.append("\r\n@Data\r\n")
                .append("@AllArgsConstructor\r\n")
                .append("@NoArgsConstructor\r\n")
                .append("public class ").append(StringUtil.toUpperCaseFirstOne(tableName)).append(" {\r\n");

        body.append("}\r\n");

        File tableFile = new File(getTableFilePath(StringUtil.toUpperCaseFirstOne(tableName)));
        FileWriter fileWriter = null;
        try {
            if(tableFile.exists()) tableFile.delete();
            fileWriter = new FileWriter(tableFile);
            fileWriter.write(head.toString());
            fileWriter.write(body.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    String getTableFilePath(String tableName){
        return FileUtil.getProjectPath() + "\\src\\main\\java\\zhuky\\clear\\entity\\" + tableName + ".java";
    }
}
