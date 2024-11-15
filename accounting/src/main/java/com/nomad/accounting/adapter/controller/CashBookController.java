package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = {"/api/v1/accounting/cash-book"})
@RequiredArgsConstructor
public class CashBookController {

    @PostMapping
    public ResponseEntity<CashBookDtoResponse> create(@RequestBody @Valid CashBookCreateDtoRequest cashBookCreateDtoRequest) {

        return ResponseEntity
                .created(null)
                .body(null);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashBookDtoResponse> find(@PathVariable(name = "id") final UUID id) {

        return ResponseEntity
                .ok()
                .body(null);
    }
}

