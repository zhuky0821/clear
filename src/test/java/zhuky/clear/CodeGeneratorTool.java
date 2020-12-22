package zhuky.clear;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.service.FileImport;
import zhuky.clear.util.CodeGeneratorUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CodeGeneratorTool {

    @Autowired
    CodeGeneratorUtil util;
    @Test
    void test(){
        util.createTableFile();
    }

    @Autowired
    IgniteCache ignite;
    /*
    copy命令：
    insert into thisjsmx (SCDM, JLLX, JYFS, JSFS, YWLX, QSBZ, GHLX, JSBH, CJBH, SQBH, WTBH, JYRQ, QSRQ, JSRQ, QTRQ, WTSJ, CJSJ, XWH1, XWH2, XWHY, JSHY, TGHY, ZQZH, ZQDM1, ZQDM2, ZQLB, LTLX, QYLB, GPNF, MMBZ, SL, CJSL, ZJZH, BZ, JG1, JG2, QSJE, YHS, JSF, GHF, ZGF, SXF, QTJE1, QTJE2, QTJE3, SJSF, JGDM, FJSM)

     "01","001","001","001","业务类型","060","00A","1905090004719541","成交编号","申请编号"," ","20190509","20190509","20190510","0"," "," ","交易席位","40204","JSC52","JSC52"," ","证券账号","证券代码"," ","PT","0"," ","0","买卖标志","交收数量","成交数量","10775","RMB","价格","22.88","清算金额","印花税","经手费","过户费","证管费","0.00","0.00","0.00","0.00","实际收付","0000","Ａ股交易清算"
     */
    @Test
    void createJsmx(){
        String dataTemplate = "\"01\",\"001\",\"001\",\"001\",\"业务类型\",\"060\",\"00A\",\"1905090004719541" +
                "\",\"成交编号\",\"申请编号\",\" \",\"20190509\",\"20190509\",\"20190510\",\"0\",\" \",\" \",\"seat\"," +
                "\"40204\",\"JSC52\",\"JSC52\",\" \",\"证券账号\",\"证券代码\",\" \",\"PT\",\"0\",\" \",\"0\",\"买卖标志\"," +
                "\"交收数量\",\"成交数量\",\"10775\",\"RMB\",\"价格\",\"22.88\",\"清算金额\",\"印花税\",\"经手费\",\"过户费\"," +
                "\"证管费\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"实际收付\",\"0000\",\"Ａ股交易清算\",\"记录号\"";
        //每个股东，5000条买入，5000条卖出，2000条回购,成交编号为 i + 组合序号 * 100000,申请编号为 组合序号*10 + （1|2）
        //买入价格21，卖出价格22，买入200股，卖出100股，印花税 2 经手费 1 过户费 1 证管费 1 证券代码100001 和 600001循环买卖

        List<List<?>> all = ignite.query(new SqlFieldsQuery("SELECT SHAREHOLDER_ID, BIND_SEAT, COMBI_ID FROM tshareholder order by combi_id")).getAll();
        List<JsmxTemplate> jsmxTemplates = new ArrayList<>();
        int recordId = 0;
        for (List<?> shareholder : all) {

            String shareholdeerId = (String) shareholder.get(0);
            String bindSeat = (String) shareholder.get(1);
            int combiId = (Integer) shareholder.get(2);
            //100001买入 200股
            for(int i=0; i<5000; i++){
                JsmxTemplate jsmxTemplate = new JsmxTemplate();
                jsmxTemplate.setYwlx("001");
                int cjbh = combiId * 100000 + i;
                jsmxTemplate.setCjbh(cjbh);
                jsmxTemplate.setSqbh(combiId*10 + 1);
                jsmxTemplate.setZqzh(shareholdeerId);
                jsmxTemplate.setZqdm("600001");
                jsmxTemplate.setMmbz("B");
                jsmxTemplate.setJssl(new BigDecimal(200));
                jsmxTemplate.setCjsl(new BigDecimal(200));
                jsmxTemplate.setJg(new BigDecimal(21));
                jsmxTemplate.setQsje(new BigDecimal(21 * 200));
                jsmxTemplate.setYhs(new BigDecimal(2));
                jsmxTemplate.setJsf(new BigDecimal(1));
                jsmxTemplate.setGhf(new BigDecimal(1));
                jsmxTemplate.setZgf(new BigDecimal(1));
                jsmxTemplate.setSjsf(new BigDecimal(-21*200 -5));
                jsmxTemplate.setRecordId(recordId);
                recordId ++;

                jsmxTemplates.add(jsmxTemplate);

                JsmxTemplate jsmxTemplate1 = new JsmxTemplate();
                jsmxTemplate1.setYwlx("003");
                cjbh = combiId * 100000 + i;
                jsmxTemplate1.setCjbh(cjbh);
                jsmxTemplate1.setSqbh(combiId*10 + 2);
                jsmxTemplate1.setZqzh(shareholdeerId);
                jsmxTemplate1.setZqdm("100001");
                jsmxTemplate1.setMmbz("B");
                jsmxTemplate1.setJssl(new BigDecimal(200));
                jsmxTemplate1.setCjsl(new BigDecimal(200));
                jsmxTemplate1.setJg(new BigDecimal(21));
                jsmxTemplate1.setQsje(new BigDecimal(21 * 200));
                jsmxTemplate1.setYhs(new BigDecimal(2));
                jsmxTemplate1.setJsf(new BigDecimal(1));
                jsmxTemplate1.setGhf(new BigDecimal(1));
                jsmxTemplate1.setZgf(new BigDecimal(1));
                jsmxTemplate1.setSjsf(new BigDecimal(-21*200 -5));
                jsmxTemplate1.setRecordId(recordId);
                recordId ++;

                jsmxTemplates.add(jsmxTemplate1);

                JsmxTemplate jsmxTemplate2 = new JsmxTemplate();
                jsmxTemplate2.setYwlx("001");
                cjbh = combiId * 100000 + i;
                jsmxTemplate2.setCjbh(cjbh);
                jsmxTemplate2.setSqbh(combiId*10 + 3);
                jsmxTemplate2.setZqzh(shareholdeerId);
                jsmxTemplate2.setZqdm("600001");
                jsmxTemplate2.setMmbz("S");
                jsmxTemplate2.setJssl(new BigDecimal(-100));
                jsmxTemplate2.setCjsl(new BigDecimal(-100));
                jsmxTemplate2.setJg(new BigDecimal(22));
                jsmxTemplate2.setQsje(new BigDecimal(22 * 100));
                jsmxTemplate2.setYhs(new BigDecimal(2));
                jsmxTemplate2.setJsf(new BigDecimal(1));
                jsmxTemplate2.setGhf(new BigDecimal(1));
                jsmxTemplate2.setZgf(new BigDecimal(1));
                jsmxTemplate2.setSjsf(new BigDecimal(22*100 -5));
                jsmxTemplate2.setRecordId(recordId);
                recordId ++;

                jsmxTemplates.add(jsmxTemplate2);

                JsmxTemplate jsmxTemplate3 = new JsmxTemplate();
                jsmxTemplate3.setYwlx("003");
                cjbh = combiId * 100000 + i;
                jsmxTemplate3.setCjbh(cjbh);
                jsmxTemplate3.setSqbh(combiId*10 + 4);
                jsmxTemplate3.setZqzh(shareholdeerId);
                jsmxTemplate3.setZqdm("100001");
                jsmxTemplate3.setMmbz("S");
                jsmxTemplate3.setJssl(new BigDecimal(-100));
                jsmxTemplate3.setCjsl(new BigDecimal(-100));
                jsmxTemplate3.setJg(new BigDecimal(22));
                jsmxTemplate3.setQsje(new BigDecimal(22 * 100));
                jsmxTemplate3.setYhs(new BigDecimal(2));
                jsmxTemplate3.setJsf(new BigDecimal(1));
                jsmxTemplate3.setGhf(new BigDecimal(1));
                jsmxTemplate3.setZgf(new BigDecimal(1));
                jsmxTemplate3.setSjsf(new BigDecimal(22*100 -5));
                jsmxTemplate3.setRecordId(recordId);
                recordId ++;

                jsmxTemplates.add(jsmxTemplate3);
            }

        }

        File file = new File("d:\\jsmx.csv");
        if(file.exists()) file.delete();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            for (JsmxTemplate jsmxTemplate : jsmxTemplates) {
                String data = dataTemplate.replaceAll("业务类型", jsmxTemplate.getYwlx())
                        .replaceAll("成交编号", String.valueOf(jsmxTemplate.getCjbh()))
                        .replaceAll("申请编号", String.valueOf(jsmxTemplate.getSqbh()))
                        .replaceAll("证券账号", jsmxTemplate.getZqzh())
                        .replaceAll("证券代码", jsmxTemplate.getZqdm())
                        .replaceAll("买卖标志", jsmxTemplate.getMmbz())
                        .replaceAll("交收数量", String.valueOf(jsmxTemplate.getJssl()))
                        .replaceAll("成交数量", String.valueOf(jsmxTemplate.getJssl()))
                        .replaceAll("价格", String.valueOf(jsmxTemplate.getJg()))
                        .replaceAll("清算金额", String.valueOf(jsmxTemplate.getQsje()))
                        .replaceAll("印花税", String.valueOf(jsmxTemplate.getYhs()))
                        .replaceAll("经手费", String.valueOf(jsmxTemplate.getJsf()))
                        .replaceAll("过户费", String.valueOf(jsmxTemplate.getGhf()))
                        .replaceAll("证管费", String.valueOf(jsmxTemplate.getZgf()))
                        .replaceAll("实际收付", String.valueOf(jsmxTemplate.getSjsf()))
                        .replaceAll("记录号", String.valueOf(jsmxTemplate.getRecordId()));

                fileWriter.write(data);
                fileWriter.write("\r\n");
            }
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

    @Autowired
    FileImport fileImport;
    @Test
    void testImport(){
        fileImport.importFile("d:\\jsmx.csv", "tjsmx");
    }

}

class JsmxTemplate{
    private String ywlx;
    private int cjbh;
    private int sqbh;
    private String zqzh;
    private String zqdm;
    private String mmbz;
    private BigDecimal jssl;
    private BigDecimal cjsl;
    private BigDecimal jg;
    private BigDecimal qsje;
    private BigDecimal yhs;
    private BigDecimal jsf;
    private BigDecimal ghf;
    private BigDecimal zgf;
    private BigDecimal sjsf;
    private int recordId;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public int getCjbh() {
        return cjbh;
    }

    public void setCjbh(int cjbh) {
        this.cjbh = cjbh;
    }

    public int getSqbh() {
        return sqbh;
    }

    public void setSqbh(int sqbh) {
        this.sqbh = sqbh;
    }

    public String getZqzh() {
        return zqzh;
    }

    public void setZqzh(String zqzh) {
        this.zqzh = zqzh;
    }

    public String getZqdm() {
        return zqdm;
    }

    public void setZqdm(String zqdm) {
        this.zqdm = zqdm;
    }

    public String getMmbz() {
        return mmbz;
    }

    public void setMmbz(String mmbz) {
        this.mmbz = mmbz;
    }

    public BigDecimal getJssl() {
        return jssl;
    }

    public void setJssl(BigDecimal jssl) {
        this.jssl = jssl;
    }

    public BigDecimal getCjsl() {
        return cjsl;
    }

    public void setCjsl(BigDecimal cjsl) {
        this.cjsl = cjsl;
    }

    public BigDecimal getJg() {
        return jg;
    }

    public void setJg(BigDecimal jg) {
        this.jg = jg;
    }

    public BigDecimal getQsje() {
        return qsje;
    }

    public void setQsje(BigDecimal qsje) {
        this.qsje = qsje;
    }

    public BigDecimal getYhs() {
        return yhs;
    }

    public void setYhs(BigDecimal yhs) {
        this.yhs = yhs;
    }

    public BigDecimal getJsf() {
        return jsf;
    }

    public void setJsf(BigDecimal jsf) {
        this.jsf = jsf;
    }

    public BigDecimal getGhf() {
        return ghf;
    }

    public void setGhf(BigDecimal ghf) {
        this.ghf = ghf;
    }

    public BigDecimal getZgf() {
        return zgf;
    }

    public void setZgf(BigDecimal zgf) {
        this.zgf = zgf;
    }

    public BigDecimal getSjsf() {
        return sjsf;
    }

    public void setSjsf(BigDecimal sjsf) {
        this.sjsf = sjsf;
    }
}
