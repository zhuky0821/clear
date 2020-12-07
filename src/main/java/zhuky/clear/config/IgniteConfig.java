package zhuky.clear.config;

import org.apache.ignite.springframework.boot.autoconfigure.IgniteClientConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfig {

    @Bean
    IgniteClientConfigurer configurer() {
        //其他配置使用yml配置
        return cfg -> cfg.setSendBufferSize(64*1024);
    }

    @Value("${ignite-client.addresses}")
    private String igniteAddress;

    public String getIgniteAddress() {
        return igniteAddress;
    }

    public void setIgniteAddress(String igniteAddress) {
        this.igniteAddress = igniteAddress;
        System.out.println(igniteAddress);
    }
}
