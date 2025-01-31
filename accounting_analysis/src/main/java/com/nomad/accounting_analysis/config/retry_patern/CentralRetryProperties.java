package com.nomad.accounting_analysis.config.retry_patern;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "resilience4j.retry.instances")
public class CentralRetryProperties {

    private RetryConfigProperties surplus;

    private RetryConfigProperties surplus2;

    private RetryConfigProperties debits;

    @Getter
    @Setter
    public static class RetryConfigProperties {

        private int maxAttempts;

        private Duration waitDuration;

        private boolean registerHealthIndicator;

        private boolean enableExponentialBackoff;

        private double exponentialBackoffMultiplier;

        private List<Class<? extends Throwable>> retryExceptions;

        private List<Class<? extends Throwable>> ignoreExceptions;

        private Metrics metrics;
    }

    @Getter
    @Setter
    public static class Metrics {

        private boolean enabled;
    }
}

