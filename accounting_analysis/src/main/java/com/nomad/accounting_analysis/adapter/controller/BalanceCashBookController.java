package com.nomad.accounting_analysis.adapter.controller;

import com.nomad.accounting_analysis.adapter.dto.response.BalanceCashBookDtoResponse;
import com.nomad.accounting_analysis.adapter.mapper.CashBookMapper;
import com.nomad.accounting_analysis.application.port.input.BalanceCashBookInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = {BalanceCashBookController.URI_BALANCE_CASHBOOK})
@RequiredArgsConstructor
public class BalanceCashBookController {

    protected static final String URI_BALANCE_CASHBOOK = "/api/v1/accounting-analysis/cashbook";

    private final BalanceCashBookInputPort balanceCashBookInputPort;

    private final CashBookMapper cashBookMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<BalanceCashBookDtoResponse> annual(@PathVariable(name = "id") final UUID cashBookId) {

        log.info("Controller Annual iniciado: {}", cashBookId);

        var response = Optional.of(cashBookId)
                .map(balanceCashBookInputPort::annual)
                .map(cashBookMapper::toBalanceCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller Annual concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }
}

