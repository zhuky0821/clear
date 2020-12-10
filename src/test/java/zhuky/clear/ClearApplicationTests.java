package zhuky.clear;

import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.config.ClearContext;
import zhuky.clear.dao.BaseTableQueryMapper;
import zhuky.clear.entity.Tfilecolumnconfig;
import zhuky.clear.entity.ignite.TshareholderKey;
import zhuky.clear.entity.ignite.TshareholderValue;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
class ClearApplicationTests {

	Logger logger = LoggerFactory.getLogger(ClearApplicationTests.class);

	@Test
	void contextLoads() {
	}

	//@Autowired
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

			conn.setAutoCommit(false);
			statement = conn.prepareStatement("delete from tshareholder a where a.shareholder_id = ? ");
			statement.setString(1, "holder5");
			statement.executeUpdate();

			conn.rollback();

			statement = conn.prepareStatement("delete from tshareholder a where a.shareholder_id = ? ");
			statement.setString(1, "holder4");
			statement.executeUpdate();
			conn.commit();

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

	//@Autowired
	BaseTableQueryMapper baseTableQueryMapper;
	@Test
	void testFileColumnConfig(){
		List<Tfilecolumnconfig> tjsmxs = baseTableQueryMapper.getFileColumnConfigs("tjsmx");
		for (Tfilecolumnconfig tjsmx : tjsmxs) {
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
		ClientCache<TshareholderKey, TshareholderValue> cache = ignite.cache("tshareholder");
		QueryCursor queryCursor = cache.query(new SqlFieldsQuery("select * from tshareholder"));
		List all = queryCursor.getAll();
		for (Object o : all) {
			System.out.println(o);

		}

		TshareholderKey key = new TshareholderKey("holder2", 1);

		TshareholderValue value = cache.get(key);
		System.out.println("key:" + key);
		System.out.println("value:" + value);
	}

	@Test
	void testxn(){
		logger.info("测试证券信息查询性能开始");
		//ClientCache cache = ignite.cache("tshareholder");
		for (int i = 0; i < 100000; i++) {
			SqlFieldsQuery sqlFieldsQuery = new SqlFieldsQuery(new SqlFieldsQuery("select * from tshareholder where shareholder_id = ? and mkt_id = ?"));
			sqlFieldsQuery.setArgs("holder14", 1);
			List<List<?>> all = ignite.query(sqlFieldsQuery).getAll();
			//getall()会严重影响性能
			if(i % 10000 == 0){
				logger.info("已查询次数：{}", i);
				for (List<?> objects : all) {
					logger.info(objects.toString());
				}
			}
		}

		logger.info("测试证券信息查询性能结束");

	}

	@Test
	void testxn1(){
		logger.info("测试证券信息查询性能1开始");
		try {
			Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
			Connection connection = DriverManager.getConnection("jdbc:ignite:thin://192.168.7.69:7000");
			PreparedStatement statement = connection.prepareStatement("select * from tsecurity where security_code = ? and mkt_id = ?");
			statement.setString(1, "600001");
			statement.setInt(2,1);
			for (int i = 0; i < 1000000; i++) {

				statement.executeQuery();
			}
			statement.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		logger.info("测试证券信息查询性能1结束");

	}

	@Test
	void testbfxn() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(20);

		logger.info("主线程查询证券信息开始");
		for (int i = 0; i < 1; i++) {
		}
		executorService.awaitTermination(1000, TimeUnit.SECONDS);
		logger.info("主线程查询证券信息结束");
	}

	@Autowired
	ClearContext clearContext;

	//删除和查询都必须加getall
	//SqlFieldsQuery每次只能执行一句sql
	@Test
	void testSqlQueryFiled(){
		logger.info("测试场查询");
		SqlFieldsQuery sqlFieldsQuery = new SqlFieldsQuery("select * from tbond");
		QueryCursor<List<?>> query = ignite.query(sqlFieldsQuery);
		List<List<?>> all = query.getAll();
		logger.info("查询结果：{}", all);

		logger.info("测试删除");
		ignite.query(new SqlFieldsQuery("delete from tbond")).getAll();


	}

	@Test
	void testThreadPool(){
		ExecutorService executorService = clearContext.getExecutorService();
		List<Future<String>> futures = new ArrayList<>();
//		for (int i = 0; i < 15; i++) {
//			TestThreadPool1 pool1 = new TestThreadPool1();
//			Future<String> submit = executorService.submit(pool1);
//			futures.add(submit);
//		}
//		try {
//			System.out.println("任务提交完毕");
//			executorService.awaitTermination(10, TimeUnit.SECONDS);
//			System.out.println("任务执行完毕");
//			for (Future<String> future : futures) {
//				if(future != null && future.get().trim().length() != 0){
//					System.out.println(future.get());
//				}
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//		//executorService.shutdownNow();
//		System.out.println("重复使用线程池");
//		futures = new ArrayList<>();
//		for (int i = 0; i < 15; i++) {
//			TestThreadPool1 pool1 = new TestThreadPool1();
//			Future<String> submit = executorService.submit(pool1);
//			futures.add(submit);
//		}
//		try {
//			System.out.println("任务提交完毕");
//			executorService.awaitTermination(10, TimeUnit.SECONDS);
//			System.out.println("任务执行完毕");
//			for (Future<String> future : futures) {
//				if(future != null && future.get().trim().length() != 0){
//					System.out.println(future.get());
//				}
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("使用invokeAll");
//		futures = new ArrayList<>();
//		List<TestThreadPool1> pool1s = new ArrayList<>();
//		for (int i = 0; i < 15; i++) {
//			TestThreadPool1 pool1 = new TestThreadPool1();
//			pool1s.add(pool1);
//		}
//		try {
//			System.out.println("开始提交任务");
//			futures = executorService.invokeAll(pool1s);
//			System.out.println("任务执行完毕");
//			for (Future<String> future : futures) {
//				if(future != null && future.get().trim().length() != 0){
//					System.out.println(future.get());
//				}
//			}
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//
//
//		}


		System.out.println("使用invokeAll 超时时间");
		futures = new ArrayList<>();
		List<TestThreadPool1> pool1s = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			TestThreadPool1 pool1 = new TestThreadPool1();
			pool1s.add(pool1);
		}
		try {
			System.out.println("开始提交任务");
			futures = executorService.invokeAll(pool1s, 2, TimeUnit.SECONDS);
			System.out.println("任务执行完毕");
			for (Future<String> future : futures) {
				if(future != null && future.get().trim().length() != 0){
					System.out.println(future.get());
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}catch (CancellationException e){
			System.out.println("任务被取消");
			e.printStackTrace();
		}
	}

	@Test
	void testFlow(){

	}

}

class TestThreadPool1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.sleep(1500);
		System.out.println(Thread.currentThread().getName());
		if(Thread.currentThread().getName().equals("pool-1-thread-10")){
			return "pool-1-thread-10 执行错误";
		}
		return " ";
	}
}
