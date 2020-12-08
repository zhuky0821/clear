package zhuky.clear;

import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.entity.Tbond;
import zhuky.clear.util.CodeGeneratorUtil;

import java.io.File;
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
    IgniteClient client;
    /*
    copy命令：
    insert into thisjsmx (SCDM, JLLX, JYFS, JSFS, YWLX, QSBZ, GHLX, JSBH, CJBH, SQBH, WTBH, JYRQ, QSRQ, JSRQ, QTRQ, WTSJ, CJSJ, XWH1, XWH2, XWHY, JSHY, TGHY, ZQZH, ZQDM1, ZQDM2, ZQLB, LTLX, QYLB, GPNF, MMBZ, SL, CJSL, ZJZH, BZ, JG1, JG2, QSJE, YHS, JSF, GHF, ZGF, SXF, QTJE1, QTJE2, QTJE3, SJSF, JGDM, FJSM)

     "20190509","01","001","001","001","业务类型","060","00A","1905090004719541","成交编号","申请编号"," ","20190509","20190509","20190510","0"," "," ","交易席位","40204","JSC52","JSC52"," ","证券账号","证券代码"," ","PT","0"," ","0","买卖标志","交收数量","成交数量","10775","RMB","价格","22.88","清算金额","印花税","经手费","过户费","证管费","0.00","0.00","0.00","0.00","实际收付","0000","Ａ股交易清算"
     */
    @Test
    void createJsmx(){
        String dataTemplate = "\"20190509\",\"01\",\"001\",\"001\",\"001\",\"业务类型\",\"060\",\"00A\",\"1905090004719541" +
                "\",\"成交编号\",\"申请编号\",\" \",\"20190509\",\"20190509\",\"20190510\",\"0\",\" \",\" \",\"seat\"," +
                "\"40204\",\"JSC52\",\"JSC52\",\" \",\"证券账号\",\"证券代码\",\" \",\"PT\",\"0\",\" \",\"0\",\"买卖标志\"," +
                "\"交收数量\",\"成交数量\",\"10775\",\"RMB\",\"价格\",\"22.88\",\"清算金额\",\"印花税\",\"经手费\",\"过户费\"," +
                "\"证管费\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"实际收付\",\"0000\",\"Ａ股交易清算\"";
        //每个股东，5000条买入，5000条卖出，2000条回购,成交编号为 i + 组合序号 * 100000,申请编号为 组合序号*10 + （1|2）
        //买入价格21，卖出价格22，买入200股，卖出100股，印花税 2 经手费 1 过户费 1 证管费 1 证券代码100001 和 600001循环买卖

        List<List<?>> all = client.query(new SqlFieldsQuery("SELECT SHAREHOLDER_ID, BIND_SEAT, COMBI_ID FROM tshareholder order by combi_id")).getAll();
        for (List<?> shareholder : all) {
            List<JsmxTemplate> jsmxTemplates = new ArrayList<>();
            //100001买入 200股
            for(int i=0; i<5000; i++){
                JsmxTemplate jsmxTemplate = new JsmxTemplate();
                jsmxTemplate.setYwlx("001");
            }
        }


        for (int i = 0; i < 2; i++) {
            String data = dataTemplate.replaceAll("业务类型", "001")
                    .replaceAll("成交编号", String.valueOf(i));

            System.out.println(data);
        }

    }


}

class JsmxTemplate{
    private String ywlx;
    private String cjbh;
    private String sqbh;
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

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getCjbh() {
        return cjbh;
    }

    public void setCjbh(String cjbh) {
        this.cjbh = cjbh;
    }

    public String getSqbh() {
        return sqbh;
    }

    public void setSqbh(String sqbh) {
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
