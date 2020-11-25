package zhuky.clear;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "zhuky.clear")
@MapperScan("zhuky.clear.dao")
public class ClearApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearApplication.class, args);
	}

}
