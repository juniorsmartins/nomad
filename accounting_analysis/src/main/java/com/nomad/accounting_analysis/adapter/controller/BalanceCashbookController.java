package com.nomad.accounting_analysis.adapter.controller;

import com.nomad.accounting_analysis.adapter.dto.response.BalanceCashbookDtoResponse;
import com.nomad.accounting_analysis.adapter.mapper.CashbookMapper;
import com.nomad.accounting_analysis.application.port.input.BalanceCashbookInputPort;
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
@RequestMapping(path = {BalanceCashbookController.URI_BALANCE_CASHBOOK})
@RequiredArgsConstructor
public class BalanceCashbookController {

    protected static final String URI_BALANCE_CASHBOOK = "/api/v1/accounting-analysis/cashbook";

    private final BalanceCashbookInputPort balanceCashbookInputPort;

    private final CashbookMapper cashbookMapper;

    @GetMapping(path = "/{id}")

    public ResponseEntity<BalanceCashbookDtoResponse> annual(@PathVariable(name = "id") final UUID cashBookId) {

        log.info("Controller Annual iniciado: {}", cashBookId);

        var response = Optional.of(cashBookId)
                .map(balanceCashbookInputPort::annual)
                .map(cashbookMapper::toBalanceCashbookDtoResponse)
                .orElseThrow();

        log.info("Controller Annual concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }
}

