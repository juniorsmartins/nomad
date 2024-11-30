package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import com.nomad.accounting.adapter.mapper.in.CashBookMapperIn;
import com.nomad.accounting.application.port.input.CashBookCreateInputPort;
import com.nomad.accounting.application.port.input.CashBookDeleteInputPort;
import com.nomad.accounting.application.port.output.CashBookFindAllOutputPort;
import com.nomad.accounting.application.port.input.CashBookFindByIdInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = {CashBookController.URI_CASHBOOK})
@RequiredArgsConstructor
public class CashBookController {

    protected static final String URI_CASHBOOK = "/api/v1/accounting/cash-book";

    private final CashBookCreateInputPort cashBookCreateInputPort;

    private final CashBookFindAllOutputPort cashBookFindAllOutputPort;

    private final CashBookFindByIdInputPort cashBookFindByIdInputPort;

    private final CashBookDeleteInputPort cashBookDeleteInputPort;

    private final CashBookMapperIn cashBookMapperIn;

    @PostMapping
    public ResponseEntity<CashBookDtoResponse> create(@RequestBody @Valid CashBookCreateDtoRequest cashBookCreateDtoRequest) {

        log.info("Controller Create iniciado: {}", cashBookCreateDtoRequest);

        var response = Optional.ofNullable(cashBookCreateDtoRequest)
                .map(cashBookMapperIn::toCashBook)
                .map(cashBookCreateInputPort::create)
                .map(cashBookMapperIn::toCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller Create concluído: {}", response);

        return ResponseEntity
                .created(URI.create(URI_CASHBOOK + "/" + response.cashBookId()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CashBookDtoResponse>> findAll(
        @PageableDefault(sort = "cashBookId", direction = Sort.Direction.DESC, size = 2) final Pageable pagination) {

        log.info("Controller FindAll acionado com paginação: {}", pagination);

        var response = cashBookFindAllOutputPort.findAll(pagination)
                .map(cashBookMapperIn::toCashBookDtoResponse);

        log.info("Controller FindAll concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashBookDtoResponse> findById(@PathVariable(name = "id") final UUID id) {

        log.info("Controller FindById acionado: {}", id);

        var response = Optional.of(id)
                .map(cashBookFindByIdInputPort::findById)
                .map(cashBookMapperIn::toCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller FindById concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") final UUID id) {

        log.info("Controller Delete acionado: {}", id);

        Optional.ofNullable(id)
                .ifPresentOrElse(cashBookDeleteInputPort::delete,
                        () -> {throw new NoSuchElementException();}
                );

        log.info("Controller Delete concluído: {}", id);

        return ResponseEntity
                .noContent()
                .build();
    }
}

