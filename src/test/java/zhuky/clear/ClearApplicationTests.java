package zhuky.clear;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhuky.clear.dao.FileColumnConfigMapper;
import zhuky.clear.entity.TFileColumnConfig;

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

			String copySql = "COPY FROM 'c:\\Users\\zhuky\\Desktop\\tbond.csv' INTO tbond (\n" +
					"  security_id, bond_interest) FORMAT CSV\n";

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

}
