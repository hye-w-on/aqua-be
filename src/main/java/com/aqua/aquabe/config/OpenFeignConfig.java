package com.aqua.aquabe.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.aqua.aquabe") // main class 대신 여기 설정할 수 있음
public class OpenFeignConfig {
    @Bean
    public SampleClientErrorDecoder errorDecoder() {
        return new SampleClientErrorDecoder();
    }
}
