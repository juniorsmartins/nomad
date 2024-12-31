package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookDtoResponse;
import com.nomad.accounting.adapter.mapper.CashBookMapperIn;
import com.nomad.accounting.application.port.input.CashBookCreateInputPort;
import com.nomad.accounting.application.port.input.CashBookDeleteInputPort;
import com.nomad.accounting.application.port.input.CashBookUpdateInputPort;
import com.nomad.accounting.application.port.output.CashBookFindAllOutputPort;
import com.nomad.accounting.application.port.output.CashBookFindByIdOutputPort;
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
@RequestMapping(path = {CashbookController.URI_CASHBOOK})
@RequiredArgsConstructor
public class CashbookController {

    protected static final String URI_CASHBOOK = "/api/v1/accounting/cash-book";

    private final CashBookCreateInputPort cashBookCreateInputPort;

    private final CashBookUpdateInputPort cashBookUpdateInputPort;

    private final CashBookFindAllOutputPort cashBookFindAllOutputPort;

    private final CashBookFindByIdOutputPort cashBookFindByIdOutputPort;

    private final CashBookDeleteInputPort cashBookDeleteInputPort;

    private final CashBookMapperIn cashBookMapperIn;

    @PostMapping
    public ResponseEntity<CashbookDtoResponse> create(@RequestBody @Valid CashbookCreateDtoRequest cashBookCreateDtoRequest) {

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

    @PutMapping
    public ResponseEntity<CashbookDtoResponse> update(@RequestBody @Valid CashbookUpdateDtoRequest cashBookUpdateDtoRequest) {

        log.info("Controller Update iniciado: {}", cashBookUpdateDtoRequest);

        var response = Optional.ofNullable(cashBookUpdateDtoRequest)
                .map(cashBookMapperIn::toCashBook)
                .map(cashBookUpdateInputPort::update)
                .map(cashBookMapperIn::toCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller Update concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CashbookDtoResponse>> findAll(
        @PageableDefault(sort = "cashBookId", direction = Sort.Direction.DESC, size = 12) final Pageable pagination) {

        log.info("Controller FindAll acionado com paginação: {}", pagination);

        var response = cashBookFindAllOutputPort.findAll(pagination)
                .map(cashBookMapperIn::toCashBookDtoResponse);

        log.info("Controller FindAll concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashbookDtoResponse> findById(@PathVariable(name = "id") final UUID id) {

        log.info("Controller FindById acionado: {}", id);

        var response = Optional.of(id)
                .map(cashBookFindByIdOutputPort::findById)
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

