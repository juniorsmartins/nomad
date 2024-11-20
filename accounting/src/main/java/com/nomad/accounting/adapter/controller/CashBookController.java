package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Year;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = {CashBookController.URI_CASHBOOK})
@RequiredArgsConstructor
public class CashBookController {

    protected static final String URI_CASHBOOK = "/api/v1/accounting/cash-book";

    @PostMapping
    public ResponseEntity<CashBookDtoResponse> create(@RequestBody @Valid CashBookCreateDtoRequest cashBookCreateDtoRequest) {

        var response = CashBookDtoResponse.builder()
                .document("65439940022")
                .yearReference(Year.of(1988))
                .build();

        return ResponseEntity
                .created(URI.create(URI_CASHBOOK + "/" + 1))
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashBookDtoResponse> find(@PathVariable(name = "id") final UUID id) {

        var response = CashBookDtoResponse.builder()
                .document("65439940022")
                .yearReference(Year.of(1988))
                .build();

        return ResponseEntity
                .ok()
                .body(response);
    }
}

