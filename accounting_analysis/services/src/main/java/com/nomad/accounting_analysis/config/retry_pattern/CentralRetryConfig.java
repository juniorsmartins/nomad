package com.nomad.accounting_analysis.config.retry_pattern;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CentralRetryConfig {

    private final CentralRetryProperties retryProperties;

    private final RetryRegistry retryRegistry;

    @Bean
    public Retry retrySurplus() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(retryProperties.getSurplus().getMaxAttempts())
                .waitDuration(retryProperties.getSurplus().getWaitDuration())
                .retryExceptions(toThrowableArray(retryProperties.getSurplus().getRetryExceptions()))
                .ignoreExceptions(toThrowableArray(retryProperties.getSurplus().getIgnoreExceptions()))
                .build();

        return retryRegistry.retry("surplus", retryConfig);
    }

    @Bean
    public Retry retrySurplus2() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(retryProperties.getSurplus2().getMaxAttempts())
                .waitDuration(retryProperties.getSurplus2().getWaitDuration())
                .retryExceptions(toThrowableArray(retryProperties.getSurplus2().getRetryExceptions()))
                .ignoreExceptions(toThrowableArray(retryProperties.getSurplus2().getIgnoreExceptions()))
                .build();

        return retryRegistry.retry("surplus2", retryConfig);
    }

    @Bean
    public Retry retryDebits() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(retryProperties.getDebits().getMaxAttempts())
                .waitDuration(retryProperties.getDebits().getWaitDuration())
                .retryExceptions(toThrowableArray(retryProperties.getDebits().getRetryExceptions()))
                .ignoreExceptions(toThrowableArray(retryProperties.getDebits().getIgnoreExceptions()))
                .build();

        return retryRegistry.retry("debits", retryConfig);
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Throwable>[] toThrowableArray(List<Class<? extends Throwable>> exceptions) {
        return exceptions.toArray(new Class[0]);
    }
}

