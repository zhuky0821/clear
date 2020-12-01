package zhuky.clear.config;

import org.apache.ignite.springframework.boot.autoconfigure.IgniteClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfig {

    @Bean
    IgniteClientConfigurer configurer() {
        //其他配置使用yml配置
        return cfg -> cfg.setSendBufferSize(64*1024);
    }
}
