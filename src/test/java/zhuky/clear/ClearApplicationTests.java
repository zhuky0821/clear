package zhuky.clear;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
