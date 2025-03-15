package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.InvestmentCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.InvestmentDtoResponse;
import com.nomad.accounting.adapter.dto.out.InvestmentFindDtoResponse;
import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.port.input.InvestmentCreateInputPort;
import com.nomad.accounting.application.port.output.InvestmentFindByIdOutputPort;
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
@RequestMapping(path = {InvestmentController.URI_INVESTMENT})
@RequiredArgsConstructor
public class InvestmentController {

    protected static final String URI_INVESTMENT = "/api/v1/accounting/investment";

    private final InvestmentCreateInputPort investmentCreateInputPort;

    private final InvestmentFindByIdOutputPort investmentFindByIdOutputPort;

    private final CentralMapper centralMapper;

    @PostMapping(path = "/{id}")
    public ResponseEntity<InvestmentDtoResponse> create(
            @PathVariable(name = "id") final UUID investmentId,
            @RequestBody @Valid InvestmentCreateDtoRequest investmentCreateDtoRequest) {

        log.info("Controller create iniciado para investmentId: {} {}", investmentId, investmentCreateDtoRequest);

        var response = Optional.ofNullable(investmentCreateDtoRequest)
                        .map(centralMapper::toInvestment)
                        .map(investment -> investmentCreateInputPort.create(investmentId, investment))
                        .map(centralMapper::toInvestmentDtoResponse)
                        .orElseThrow();

        log.info("Controller create concluído: {}", response);

        return ResponseEntity
                .created(URI.create(URI_INVESTMENT + "/" + investmentId))
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InvestmentFindDtoResponse> findById(@PathVariable(name = "id") final UUID id) {

        log.info("Controller findById iniciado: {}", id);

        var response = Optional.of(id)
                .map(investmentFindByIdOutputPort::findById)
                .map(centralMapper::toInvestmentFindDtoResponse)
                .orElseThrow();

        log.info("Controller findById concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }
}

