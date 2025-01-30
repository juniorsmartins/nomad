package com.nomad.accounting_analysis.config.bean;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.SocketTimeoutException;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class CentralRetryConfig {

    public static final String SURPLUS2 = "surplus2";

    public static final String DEBITS = "debits";

    private final RetryRegistry retryRegistry;

    @Bean
    public Retry retrySurplus2() {
        RetryConfig retryConfig = retryConfigSurplus2();
        return retryRegistry.retry(SURPLUS2, retryConfig);
    }

    @Bean
    public Retry retryDebits() {
        RetryConfig retryConfig = retryConfigDebits();
        return retryRegistry.retry(DEBITS, retryConfig);
    }

    private RetryConfig retryConfigSurplus2() {

        return RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(WebClientResponseException.class, WebClientRequestException.class,
                        HttpServerErrorException.class, ResourceAccessException.class, SocketTimeoutException.class)
                .build();
    }

    private RetryConfig retryConfigDebits() {

        return RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(1))
                .retryExceptions(WebClientResponseException.class, WebClientRequestException.class,
                        HttpServerErrorException.class, ResourceAccessException.class, SocketTimeoutException.class)
                .build();
    }
}

