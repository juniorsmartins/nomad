package com.nomad.accounting_analysis.config.bean;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RetryConfiguration {

    private final RetryRegistry retryRegistry;

//    @Bean
//    public Retry
}

