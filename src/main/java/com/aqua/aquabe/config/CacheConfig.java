package com.aqua.aquabe.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        // ConcurrentMapCacheManager : 메모리에 캐시저장
        // Encache
        /*
         * CacheConfiguration<Long, String> cacheConfiguration =
         * CacheConfigurationBuilder
         * .newCacheConfigurationBuilder(Long.class, String.class,
         * ResourcePoolsBuilder.heap(100))
         * .withExpiry(Expirations.timeToLiveExpiration(Duration.of(20,
         * TimeUnit.SECONDS)))
         * .build();
         */
        return new ConcurrentMapCacheManager("addresses");
    }
}