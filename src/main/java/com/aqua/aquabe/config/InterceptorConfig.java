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
import com.aqua.aquabe.interceptor.EmployeeAuthInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private final AuthenticationInterceptor authInterceptor;

    @Autowired
    private final EmployeeAuthInterceptor employeeAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urlPatterns = Arrays.asList("/**");
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(urlPatterns)
                .excludePathPatterns("/v1/session/signup")
                .excludePathPatterns("/v1/session/autoLogin")
                .excludePathPatterns("/v1/http-session/employee")
                .excludePathPatterns("/v1/sample/**")
                .excludePathPatterns("/health", "/error")
                .excludePathPatterns("/swagger-ui/**", "/swagger-resources/**",
                        "/v3/api-docs")
                .excludePathPatterns("/v1/http-session/scope")
                .excludePathPatterns("/v1/admin/**");

        registry.addInterceptor(employeeAuthInterceptor)
                .addPathPatterns("/v1/http-session/scope")
                .addPathPatterns("/v1/admin/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addConverter(new ContentsTypeCodeConverter());
        // registry.addConverter(new TermsUseCodeConverter());
    }
}
