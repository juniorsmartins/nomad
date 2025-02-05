package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.filter.CashbookFilter;
import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookCreateDtoResponse;
import com.nomad.accounting.adapter.dto.out.CashbookFindDtoResponse;
import com.nomad.accounting.adapter.dto.out.CashbookSearchDtoResponse;
import com.nomad.accounting.adapter.dto.out.CashbookUpdateDtoResponse;
import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.port.input.CashbookCreateInputPort;
import com.nomad.accounting.application.port.input.CashbookDeleteInputPort;
import com.nomad.accounting.application.port.input.CashbookUpdateInputPort;
import com.nomad.accounting.application.port.output.CashbookFindAllOutputPort;
import com.nomad.accounting.application.port.output.CashbookFindByIdOutputPort;
import com.nomad.accounting.application.port.output.CashbookSearchOutputPort;
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
@RequestMapping(path = {CashbookController.URI_CASHBOOK})
@RequiredArgsConstructor
public class CashbookController {

    protected static final String URI_CASHBOOK = "/api/v1/accounting/cashbook";

    private final CashbookCreateInputPort cashBookCreateInputPort;

    private final CashbookUpdateInputPort cashBookUpdateInputPort;

    private final CashbookFindAllOutputPort cashBookFindAllOutputPort;

    private final CashbookFindByIdOutputPort cashBookFindByIdOutputPort;

    private final CashbookSearchOutputPort cashBookSearchOutputPort;

    private final CashbookDeleteInputPort cashBookDeleteInputPort;

    private final CentralMapper centralMapper;

    @PostMapping
    public ResponseEntity<CashbookCreateDtoResponse> create(@RequestBody @Valid CashbookCreateDtoRequest cashBookCreateDtoRequest) {

        log.info("Controller Create iniciado: {}", cashBookCreateDtoRequest);

        var response = Optional.ofNullable(cashBookCreateDtoRequest)
                .map(centralMapper::toCashbook)
                .map(cashBookCreateInputPort::create)
                .map(centralMapper::toCashbookCreateDtoResponse)
                .orElseThrow();

        log.info("Controller Create concluído: {}", response);

        return ResponseEntity
                .created(URI.create(URI_CASHBOOK + "/" + response.cashbookId()))
                .body(response);
    }

    @PutMapping
    public ResponseEntity<CashbookUpdateDtoResponse> update(@RequestBody @Valid CashbookUpdateDtoRequest cashBookUpdateDtoRequest) {

        log.info("Controller Update iniciado: {}", cashBookUpdateDtoRequest);

        var response = Optional.ofNullable(cashBookUpdateDtoRequest)
                .map(centralMapper::toCashbook)
                .map(cashBookUpdateInputPort::update)
                .map(centralMapper::toCashbookUpdateDtoResponse)
                .orElseThrow();

        log.info("Controller Update concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CashbookFindDtoResponse>> findAll(
            @PageableDefault(sort = "cashbookId", direction = Sort.Direction.DESC, size = 12) final Pageable pagination) {

        log.info("Controller FindAll acionado com paginação: {}", pagination);

        var response = cashBookFindAllOutputPort.findAll(pagination)
                .map(centralMapper::toCashbookFindDtoResponse);

        log.info("Controller FindAll concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashbookFindDtoResponse> findById(@PathVariable(name = "id") final UUID id) {

        log.info("Controller FindById acionado: {}", id);

        var response = Optional.of(id)
                .map(cashBookFindByIdOutputPort::findById)
                .map(centralMapper::toCashbookFindDtoResponse)
                .orElseThrow();

        log.info("Controller FindById concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Page<CashbookSearchDtoResponse>> search(@Valid final CashbookFilter cashBookFilter,
                                                                  @PageableDefault(sort = "cashbookId", direction = Sort.Direction.DESC, size = AccountingConstants.PAGE_SIZE) final Pageable pagination) {

        log.info("Controller Search acionado: {}", cashBookFilter);

        var response = cashBookSearchOutputPort.search(cashBookFilter, pagination)
                .map(centralMapper::toCashbookSearchDtoResponse);

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
                        () -> {
                            throw new NoSuchElementException();
                        }
                );

        log.info("Controller Delete concluído: {}", id);

        return ResponseEntity
                .noContent()
                .build();
    }
}

