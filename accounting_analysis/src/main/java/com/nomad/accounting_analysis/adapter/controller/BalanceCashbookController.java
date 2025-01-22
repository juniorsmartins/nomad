package com.nomad.accounting_analysis.adapter.controller;

import com.nomad.accounting_analysis.adapter.mapper.CashbookMapper;
import com.nomad.accounting_analysis.application.port.input.BalanceCashbookInputPort;
import com.nomad.accounting_analysis.config.exception.http404.CashbookNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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

@Slf4j
@RestController
@RequestMapping(path = {BalanceCashbookController.URI_BALANCE_CASHBOOK})
@RequiredArgsConstructor
public class BalanceCashbookController {

    protected static final String URI_BALANCE_CASHBOOK = "/api/v1/accounting-analysis/cashbook";

    private final BalanceCashbookInputPort balanceCashbookInputPort;

    private final CashbookMapper cashbookMapper;

    @GetMapping(path = "/{id}")
    @Retry(name = "default")
    @CircuitBreaker(name = "default", fallbackMethod = "getFallbackAnnual")
    public ResponseEntity<Object> annual(@PathVariable(name = "id") final UUID cashbookId) {

        log.info("Controller Annual iniciado: {}", cashbookId);

        var response = Optional.of(cashbookId)
                .map(balanceCashbookInputPort::annual)
                .map(cashbookMapper::toBalanceCashbookDtoResponse)
                .orElseThrow();

        log.info("Controller Annual concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    private ResponseEntity<String> getFallbackAnnual(final UUID cashbookId, CashbookNotFoundException exception) {
        log.info("Não encontrado Cashbook com identificador: {}.", cashbookId, exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(String.format("Não encontrado Cashbook com identificador: %s", cashbookId));
    }

    private ResponseEntity<String> getFallbackAnnual(final UUID cashbookId, RuntimeException exception) {
        log.info("Falha ao buscar relatório anual com cashbookId: {}.", cashbookId, exception);
        return ResponseEntity
                .internalServerError()
                .body(String
                        .format("Falha ao buscar relatório anual com cashbookId: %s. Tente novamente mais tarde.", cashbookId));
    }
}

