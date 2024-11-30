package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import com.nomad.accounting.adapter.mapper.in.CashBookMapperIn;
import com.nomad.accounting.application.port.input.CashBookCreateInputPort;
import com.nomad.accounting.application.port.input.CashBookFindInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = {CashBookController.URI_CASHBOOK})
@RequiredArgsConstructor
public class CashBookController {

    protected static final String URI_CASHBOOK = "/api/v1/accounting/cash-book/";

    private final CashBookCreateInputPort cashBookCreateInputPort;

    private final CashBookFindInputPort cashBookFindInputPort;

    private final CashBookMapperIn cashBookMapperIn;

    @PostMapping
    public ResponseEntity<CashBookDtoResponse> create(@RequestBody @Valid CashBookCreateDtoRequest cashBookCreateDtoRequest) {

        log.info("Controller iniciado: {}", cashBookCreateDtoRequest);

        var response = Optional.ofNullable(cashBookCreateDtoRequest)
                .map(cashBookMapperIn::toCashBook)
                .map(cashBookCreateInputPort::create)
                .map(cashBookMapperIn::toCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller concluído: {}", response);

        return ResponseEntity
                .created(URI.create(URI_CASHBOOK + response.cashBookId()))
                .body(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CashBookDtoResponse> find(@PathVariable(name = "id") final UUID id) {

        log.info("Controller acionado: {}", id);

        var response = Optional.of(id)
                .map(cashBookFindInputPort::findById)
                .map(cashBookMapperIn::toCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }
}

