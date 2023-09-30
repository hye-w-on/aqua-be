package com.aqua.aquabe.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aqua.aquabe.interceptor.AuthenticationInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private final AuthenticationInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urlPatterns = Arrays.asList("/**");
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(urlPatterns)
                .excludePathPatterns("/v1/session/signup")
                .excludePathPatterns("/v1/session/autoLogin")
                .excludePathPatterns("/v1/sample/**")
                .excludePathPatterns("/health", "/error")
                .excludePathPatterns("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addConverter(new ContentsTypeCodeConverter());
        // registry.addConverter(new TermsUseCodeConverter());
    }
}
