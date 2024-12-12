package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.filter.CashBookFilter;
import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashBookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.*;
import com.nomad.accounting.adapter.mapper.CashBookMapperIn;
import com.nomad.accounting.application.port.input.CashBookCreateInputPort;
import com.nomad.accounting.application.port.input.CashBookDeleteInputPort;
import com.nomad.accounting.application.port.input.CashBookUpdateInputPort;
import com.nomad.accounting.application.port.output.CashBookFindAllOutputPort;
import com.nomad.accounting.application.port.output.CashBookFindByIdOutputPort;
import com.nomad.accounting.application.port.output.CashBookSearchOutputPort;
import com.nomad.accounting.config.AccountingConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = {CashBookController.URI_CASHBOOK})
@RequiredArgsConstructor
public class CashBookController {

    protected static final String URI_CASHBOOK = "/api/v1/accounting/cashbook";

    private final CashBookCreateInputPort cashBookCreateInputPort;

    private final CashBookUpdateInputPort cashBookUpdateInputPort;

    private final CashBookFindAllOutputPort cashBookFindAllOutputPort;

    private final CashBookFindByIdOutputPort cashBookFindByIdOutputPort;

    private final CashBookSearchOutputPort cashBookSearchOutputPort;

    private final CashBookDeleteInputPort cashBookDeleteInputPort;

    private final CashBookMapperIn cashBookMapperIn;

    @PostMapping
    public ResponseEntity<CashBookCreateDtoResponse> create(@RequestBody @Valid CashBookCreateDtoRequest cashBookCreateDtoRequest) {

        log.info("Controller Create iniciado: {}", cashBookCreateDtoRequest);

        var response = Optional.ofNullable(cashBookCreateDtoRequest)
                .map(cashBookMapperIn::toCashBook)
                .map(cashBookCreateInputPort::create)
                .map(cashBookMapperIn::toCashBookCreateDtoResponse)
                .orElseThrow();

        log.info("Controller Create concluído: {}", response);

        return ResponseEntity
                .created(URI.create(URI_CASHBOOK + "/" + response.cashbookId()))
                .body(response);
    }

    @PutMapping
    public ResponseEntity<CashBookUpdateDtoResponse> update(@RequestBody @Valid CashBookUpdateDtoRequest cashBookUpdateDtoRequest) {

        log.info("Controller Update iniciado: {}", cashBookUpdateDtoRequest);

        var response = Optional.ofNullable(cashBookUpdateDtoRequest)
                .map(cashBookMapperIn::toCashBook)
                .map(cashBookUpdateInputPort::update)
                .map(cashBookMapperIn::toCashBookUpdateDtoResponse)
                .orElseThrow();

        log.info("Controller Update concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CashBookFindDtoResponse>> findAll(
        @PageableDefault(sort = "cashbookId", direction = Sort.Direction.DESC, size = 12) final Pageable pagination) {

        log.info("Controller FindAll acionado com paginação: {}", pagination);

        var response = cashBookFindAllOutputPort.findAll(pagination)
                .map(cashBookMapperIn::toCashBookFindDtoResponse);

        log.info("Controller FindAll concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashBookFindDtoResponse> findById(@PathVariable(name = "id") final UUID id) {

        log.info("Controller FindById acionado: {}", id);

        var response = Optional.of(id)
                .map(cashBookFindByIdOutputPort::findById)
                .map(cashBookMapperIn::toCashBookFindDtoResponse)
                .orElseThrow();

        log.info("Controller FindById concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Page<CashBookSearchDtoResponse>> search(@Valid final CashBookFilter cashBookFilter,
          @PageableDefault(sort = "cashbookId", direction = Sort.Direction.DESC, size = AccountingConstants.PAGE_SIZE)
          final Pageable pagination) {

        log.info("Controller Search acionado: {}", cashBookFilter);

        var response = cashBookSearchOutputPort.search(cashBookFilter, pagination)
            .map(cashBookMapperIn::toCashBookSearchDtoResponse);

        log.info("Controller Search concluído: {}", response);

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

