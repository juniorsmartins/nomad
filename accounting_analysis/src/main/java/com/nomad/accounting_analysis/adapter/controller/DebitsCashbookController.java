package com.nomad.accounting_analysis.adapter.controller;

import com.nomad.accounting_analysis.adapter.mapper.CashbookMapper;
import com.nomad.accounting_analysis.application.port.input.DebitsCashbookInputPort;
import com.nomad.accounting_analysis.config.exception.http404.CashbookNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping(path = {DebitsCashbookController.URI_BALANCE_CASHBOOK})
public class DebitsCashbookController {

    protected static final String URI_BALANCE_CASHBOOK = "/api/v1/accounting-analysis/cashbook";

    private final DebitsCashbookInputPort debitsCashbookInputPort;

    private final CashbookMapper cashbookMapper;

    private final Retry retry;

    public DebitsCashbookController(
            DebitsCashbookInputPort debitsCashbookInputPort,
            CashbookMapper cashbookMapper,
            @Qualifier("retryDebits") Retry retry
    ) {
        this.debitsCashbookInputPort = debitsCashbookInputPort;
        this.cashbookMapper = cashbookMapper;
        this.retry = retry;
    }

    @GetMapping(path = "/debits/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "getFallbackDebits")
    public ResponseEntity<Object> debits(@PathVariable(name = "id") final UUID cashbookId) {

        log.info("classe=controller metodo=debits - Iniciado com id: {}", cashbookId);

        Supplier<Object> supplier = Retry.decorateSupplier(retry, () -> {
            return Optional.of(cashbookId)
                    .map(debitsCashbookInputPort::debits)
                    .map(cashbookMapper::toBalanceCashbookDtoResponse)
                    .orElseThrow();
        });

        Object response = supplier.get();

        log.info("classe=controller metodo=debits - Concluído com resposta: {}", response);
        return ResponseEntity
                .ok()
                .body(response);
    }

    private ResponseEntity<String> getFallbackDebits(final UUID cashbookId, CashbookNotFoundException exception) {
        log.info("classe=controller metodo=getFallbackDebits - Não encontrado Cashbook com identificador: {}.",
                cashbookId, exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(String.format("Não encontrado Cashbook com identificador: %s", cashbookId));
    }

    private ResponseEntity<String> getFallbackDebits(final UUID cashbookId, RuntimeException exception) {
        log.info("classe=controller metodo=getFallbackDebits - Falha ao buscar relatório com cashbookId: {}.",
                cashbookId, exception);

        return ResponseEntity
                .internalServerError()
                .body(String.format("Falha ao buscar relatório com cashbookId: %s. Tente mais tarde.", cashbookId));
    }
}

