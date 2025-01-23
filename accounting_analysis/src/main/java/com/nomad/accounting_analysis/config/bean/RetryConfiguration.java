package com.nomad.accounting_analysis.config.bean;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RetryConfiguration {

    private static final String SURPLUS2 = "surplus2";

    private final RetryRegistry retryRegistry;

    @Bean
    public Retry surplus2RetryConfig() {

        RetryConfig surplus2RetryConfig = RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(WebClientResponseException.class, HttpServerErrorException.class, RuntimeException.class)
                .build();

        return retryRegistry.retry(SURPLUS2, surplus2RetryConfig);
    }
}

