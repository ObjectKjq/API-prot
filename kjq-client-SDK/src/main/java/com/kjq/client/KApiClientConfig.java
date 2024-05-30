package com.kjq.client;

import com.kjq.client.client.KApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 读取配置文件中的ak/sk
 */
@Configuration
@ConfigurationProperties("kjq.client")
@Data
@ComponentScan
public class KApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public KApiClient kApiClient(){
        return new KApiClient(accessKey, secretKey);
    }
}
