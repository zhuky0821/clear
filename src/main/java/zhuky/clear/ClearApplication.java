package zhuky.clear;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.logger.log4j2.Log4J2Logger;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClearApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearApplication.class, args);
	}


	@Bean
	public IgniteConfigurer configurer() {
		return cfg -> {
			//Setting consistent id.
			//See `application.yml` for the additional properties.
			cfg.setConsistentId("clear");
		};
	}

}
