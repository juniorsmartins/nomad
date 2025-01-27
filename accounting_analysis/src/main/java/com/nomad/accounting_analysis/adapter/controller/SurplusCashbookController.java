package com.nomad.accounting_analysis.adapter.controller;

import com.nomad.accounting_analysis.adapter.mapper.CashbookMapper;
import com.nomad.accounting_analysis.application.port.input.SurplusCashbookInputPort;
import com.nomad.accounting_analysis.config.exception.http404.CashbookNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static com.nomad.accounting_analysis.config.bean.RetryRegistryConfiguration.SURPLUS2;

@Slf4j
@RestController
@RequestMapping(path = {SurplusCashbookController.URI_BALANCE_CASHBOOK})
@RequiredArgsConstructor
public class SurplusCashbookController {

    protected static final String URI_BALANCE_CASHBOOK = "/api/v1/accounting-analysis/cashbook";

    private final SurplusCashbookInputPort surplusCashbookInputPort;

    private final CashbookMapper cashbookMapper;

    private final RetryRegistry retryRegistry;

    @GetMapping(path = "/surplus/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "getFallbackSurplus")
    public ResponseEntity<Object> surplus(@PathVariable(name = "id") final UUID cashbookId) {

        log.info("classe=controller metodo=surplus - Iniciado com id: {}", cashbookId);

        var retry = retryRegistry.retry(SURPLUS2);

        Supplier<Object> supplier = Retry.decorateSupplier(retry, () -> {
            return Optional.of(cashbookId)
                    .map(surplusCashbookInputPort::surplus)
                    .map(cashbookMapper::toBalanceCashbookDtoResponse)
                    .orElseThrow();
        });

        Object response;

        try {
            response = supplier.get();
            log.info("classe=controller metodo=surplus - Concluído com resposta: {}", response);

        } catch (Exception ex) {
            log.info("classe=controller metodo=surplus - Erro ao executar a operação com Retry.", ex);
            return ResponseEntity
                    .internalServerError()
                    .build();
        }

        return ResponseEntity
                .ok()
                .body(response);
    }

    private ResponseEntity<String> getFallbackSurplus(final UUID cashbookId, CashbookNotFoundException exception) {
        log.info("classe=controller metodo=getFallbackSurplus - Não encontrado Cashbook com identificador: {}.",
                cashbookId, exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(String.format("Não encontrado Cashbook com identificador: %s", cashbookId));
    }

    private ResponseEntity<String> getFallbackSurplus(final UUID cashbookId, RuntimeException exception) {
        log.info("classe=controller metodo=getFallbackSurplus - Falha ao buscar relatório com cashbookId: {}.",
                cashbookId, exception);

        return ResponseEntity
                .internalServerError()
                .body(String
                    .format("Falha ao buscar relatório com cashbookId: %s. Tente mais tarde.", cashbookId));
    }
}

