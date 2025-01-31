package com.nomad.accounting_analysis.config.retry_patern;

import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CentralRetryRegistry {

    @Bean
    public RetryRegistry retryRegistry(RegistryEventConsumer<Retry> retryRegistryEventConsumer) {
        return RetryRegistry.of(new HashMap<>(), retryRegistryEventConsumer);
    }
}

