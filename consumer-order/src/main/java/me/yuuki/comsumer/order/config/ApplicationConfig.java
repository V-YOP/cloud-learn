package me.yuuki.comsumer.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    @LoadBalanced // 使能够通过微服务名称发现微服务的地址
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
