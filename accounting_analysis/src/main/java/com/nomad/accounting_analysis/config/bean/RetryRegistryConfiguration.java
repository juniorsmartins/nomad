package com.nomad.accounting_analysis.config.bean;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.rmi.registry.Registry;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RetryRegistryConfiguration {

    public static final String SURPLUS2 = "surplus2";

    public static final String DEBITS = "debits";

    @Bean
    public RetryRegistry retryRegistry() {

        RetryConfig retryConfigSurplus2 = retryConfigSurplus2();
        RetryConfig retryConfigDebits = retryConfigDebits();

        Map<String, RetryConfig> configMap = new HashMap<>();
        configMap.put(SURPLUS2, retryConfigSurplus2);
        configMap.put(DEBITS, retryConfigDebits);

        return RetryRegistry.of(configMap);
    }

    private RetryConfig retryConfigSurplus2() {

        return RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(WebClientResponseException.class, HttpServerErrorException.class, RuntimeException.class)
                .build();
    }

    private RetryConfig retryConfigDebits() {

        return RetryConfig.custom()
                .maxAttempts(4)
                .waitDuration(Duration.ofSeconds(1))
                .retryExceptions(WebClientResponseException.class, HttpServerErrorException.class, RuntimeException.class)
                .build();
    }
}

