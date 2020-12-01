package zhuky.clear;

import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.dao.FileColumnConfigMapper;
import zhuky.clear.entity.TFileColumnConfig;
import zhuky.clear.entity.Tproduct;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@SpringBootTest
class ClearApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	DataSource dataSource;
	//private DataSource dataSource;
	@Test
	void testJdbc(){
		try {
			Connection conn = dataSource.getConnection();

			PreparedStatement statement = conn.prepareStatement("select * from tproduct");

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()){
				System.out.println(resultSet.getInt("product_id") + " " + resultSet.getString("product_code"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCopy(){
		try {
			Connection connection = dataSource.getConnection();

			String copySql = "COPY FROM 'c:\\Users\\zhuky\\Desktop\\1111.csv' INTO tjsmx (\n" +
					"  SCDM,JLLX,JYFS,JSFS,YWLX,QSBZ,GHLX,JSBH,CJBH,SQBH,WTBH,JYRQ,QSRQ,JSRQ,QTRQ,WTSJ,CJSJ,XWH1,XWH2,XWHY,JSHY,TGHY,ZQZH,ZQDM1,ZQDM2,ZQLB,LTLX,QYLB,GPNF,MMBZ,SL,CJSL,ZJZH,BZ,JG1,JG2,QSJE,YHS,JSF,GHF,ZGF,SXF,QTJE1,QTJE2,QTJE3,SJSF,JGDM,FJSM,RECORD_ID\n) FORMAT CSV\n";

			try (Statement statement = connection.createStatement()) {
			  statement.executeUpdate(copySql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	FileColumnConfigMapper fileColumnConfigMapper;
	@Test
	void testFileColumnConfig(){
		List<TFileColumnConfig> tjsmxs = fileColumnConfigMapper.getFileColumnConfigs("tjsmx");
		for (TFileColumnConfig tjsmx : tjsmxs) {
			System.out.println("private  " + tjsmx.getColumnName() + ";");
		}

	}

	@Test
	void createJsmx(){

	}

	static void createFile(String mmbz, String ywlx, int count, int zqzh){
		for(int i=0; i<count; i++){
			StringBuilder line = new StringBuilder();
			line.append("\"01\",\"001\",\"001\",\"001\",\"SSSSS\",\"060\",\"00A\",\"2008070024802194\",\"22519711\",\"0000008228\",\" \",\"20200807\",\"20200807\",\"20200810\",\"0\",\" \",\" \",\"44355\",\"44355\",\"JSE14\",\"JSE14\",\" \",\"B882752741\",\"600699\",\" \",\"PT\",\"0\",\" \",\"0\",\"B\",\"300.000\",\"300.000\",\"040000000000511268\",\"RMB\",\"22.8300000000\",\"22.8300000000\",\"-6849.00\",\"0.00\",\"-0.33\",\"-0.14\",\"-0.14\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"-6849.61\",\"0000\",\"A股交易清算\",\"20896\"\n");
		}
	}


	@Autowired
	IgniteClient ignite;
	@Test
	void testCache(){
		ClientCache<Integer, Tproduct> product = ignite.cache("tproduct1");
//		QueryCursor queryCursor = product.query(new SqlFieldsQuery("select * from tproduct"));
//		List all = queryCursor.getAll();
//		for (Object o : all) {
//			System.out.println(o);
//
//		}

		Tproduct tProduct = product.get(1);
		System.out.println(tProduct);
	}

}
