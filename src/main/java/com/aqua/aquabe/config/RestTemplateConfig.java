package com.aqua.aquabe.config;

import java.nio.charset.Charset;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
        // HttpComponentsClientHttpRequestFactory factory = new
        // HttpComponentsClientHttpRequestFactory(); 얘가 에러를 일으킴 뭐냐 너
        return restTemplateBuilder
                // .requestFactory(() -> factory)
                .setConnectTimeout(Duration.ofMillis(5000)) // connection-timeout
                .setReadTimeout(Duration.ofMillis(5000)) // read-timeout
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
