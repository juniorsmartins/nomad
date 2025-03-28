package com.nomad.accounting_analysis.controller;

import com.nomad.accounting_analysis.config.exception.http404.CashbookNotFoundException;
import com.nomad.accounting_analysis.mapper.CashbookMapperWeb;
import com.nomad.accounting_analysis.port.input.BalanceCashbookInputPort;
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

    private final CashbookMapperWeb cashbookMapper;

    @GetMapping(path = "/{id}")
    @Retry(name = "default")
    @CircuitBreaker(name = "default", fallbackMethod = "getFallbackAnnual")
    public ResponseEntity<Object> annual(@PathVariable(name = "id") final UUID cashbookId) {

        log.info("classe=controller metodo=annual - Iniciado com id: {}", cashbookId);

        var response = Optional.of(cashbookId)
                .map(balanceCashbookInputPort::annual)
                .map(cashbookMapper::toBalanceCashbookDtoResponse)
                .orElseThrow();

        log.info("classe=controller metodo=annual - Concluído com resposta: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    private ResponseEntity<String> getFallbackAnnual(final UUID cashbookId, CashbookNotFoundException exception) {
        log.info("classe=controller metodo=getFallbackAnnual - Não encontrado Cashbook com identificador: {}.",
                cashbookId, exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(String.format("Não encontrado Cashbook com identificador: %s", cashbookId));
    }

    private ResponseEntity<String> getFallbackAnnual(final UUID cashbookId, RuntimeException exception) {
        log.info("classe=controller metodo=getFallbackAnnual - Falha ao buscar relatório com cashbookId: {}.",
                cashbookId, exception);

        return ResponseEntity
                .internalServerError()
                .body(String
                    .format("Falha ao buscar relatório anual com cashbookId: %s. Tente mais tarde.", cashbookId));
    }
}

