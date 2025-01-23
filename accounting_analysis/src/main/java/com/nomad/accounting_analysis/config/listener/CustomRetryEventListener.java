package com.nomad.accounting_analysis.config.listener;

import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.event.RetryEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRetryEventListener implements RegistryEventConsumer<RetryEvent> {

    @Override
    public void onEntryAddedEvent(EntryAddedEvent<RetryEvent> entryAddedEvent) {
        log.info("Um novo registro de Retry foi adicionado no RetryRegistry: {}", entryAddedEvent.getAddedEntry());
    }

    @Override
    public void onEntryRemovedEvent(EntryRemovedEvent<RetryEvent> entryRemoveEvent) {
        log.info("Um registro de Retry foi removido do RetryRegistry: {}", entryRemoveEvent.getEventType());
    }

    @Override
    public void onEntryReplacedEvent(EntryReplacedEvent<RetryEvent> entryReplacedEvent) {
        log.info("Um registro de Retry foi substituído por outro no RetryRegistry. Antigo: {} - Novo: {}",
                entryReplacedEvent.getOldEntry(), entryReplacedEvent.getNewEntry());
    }

    // Para logar quando o retry é bem-sucedido
    public void onRetrySuccess(RetryEvent retryEvent) {
        log.info("Retry foi executado com sucesso. Detalhes do evento: {}", retryEvent);
    }

    // Para logar quando o retry falha
    public void onRetryFailure(RetryEvent retryEvent) {
        log.error("Retry falhou. Detalhes do evento: {}", retryEvent);
    }
}

