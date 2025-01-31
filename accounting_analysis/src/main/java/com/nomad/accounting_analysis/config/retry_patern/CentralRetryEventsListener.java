package com.nomad.accounting_analysis.config.retry_patern;

import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.event.RetryOnRetryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CentralRetryEventsListener {

    @Bean
    public RegistryEventConsumer<Retry> registryEventConsumer() {

        return new RegistryEventConsumer<>() {

            @Override
            public void onEntryAddedEvent(EntryAddedEvent<Retry> entryAddedEvent) {
                entryAddedEvent.getAddedEntry()
                        .getEventPublisher()
                        .onRetry(this::logRetryEvent);
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<Retry> entryRemoveEvent) {
                log.info("Retry entry removed: {}", entryRemoveEvent.getRemovedEntry().getName());
            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<Retry> entryReplacedEvent) {
                log.info("Retry entry replaced: {} -> {}",
                        entryReplacedEvent.getOldEntry().getName(),
                        entryReplacedEvent.getNewEntry().getName());
            }

            private void logRetryEvent(final RetryOnRetryEvent retryOnRetryEvent) {
                log.info("classe=CentralRetryEventsListener metodo=registryEventConsumer - name={}, type={}, numberOfRetryAttempts={}, waitInterval={} e creationTime={}",
                        retryOnRetryEvent.getName(),
                        retryOnRetryEvent.getEventType(),
                        retryOnRetryEvent.getNumberOfRetryAttempts(),
                        retryOnRetryEvent.getWaitInterval(),
                        retryOnRetryEvent.getCreationTime());
            }
        };
    }
}

