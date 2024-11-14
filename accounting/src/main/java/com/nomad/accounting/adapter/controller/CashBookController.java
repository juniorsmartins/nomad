package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashBookFilter;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@RequestMapping(path = {"/api/v1/accounting/cash-book"})
@RequiredArgsConstructor
public class CashBookController {

    @PostMapping
    public ResponseEntity<CashBookDtoResponse> create(@Valid @RequestBody CashBookCreateDtoRequest cashBookCreateDtoRequest) {

        return ResponseEntity
                .created()
                .body();
    }

    @GetMapping
    public ResponseEntity<Page<CashBookDtoResponse>> search(final CashBookFilter cashBookFilter, final Pageable pagination) {

    }
}

