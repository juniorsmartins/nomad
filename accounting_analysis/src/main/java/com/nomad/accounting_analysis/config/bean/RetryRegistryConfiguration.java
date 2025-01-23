package com.nomad.accounting_analysis.config.bean;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RetryRegistryConfiguration {

    @Bean
    public RetryRegistry retryRegistry() {

        RetryConfig retryConfig = RetryConfig.ofDefaults();
        return RetryRegistry.of(Map.of("default", retryConfig));
    }
}

