package zhuky.clear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "zhuky.clear")
public class ClearApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearApplication.class, args);
	}

}
