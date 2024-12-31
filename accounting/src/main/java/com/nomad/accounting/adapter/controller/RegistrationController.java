package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookDtoResponse;
import com.nomad.accounting.adapter.mapper.CashBookMapperIn;
import com.nomad.accounting.adapter.mapper.RegistrationMapperIn;
import com.nomad.accounting.application.port.input.RegistrationCreateInputPort;
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
@RequestMapping(path = {RegistrationController.URI_REGISTRATION})
@RequiredArgsConstructor
public class RegistrationController {

    protected static final String URI_REGISTRATION = "/api/v1/accounting/registration";

    private final RegistrationCreateInputPort registrationCreateInputPort;

    private final RegistrationMapperIn registrationMapperIn;

    private final CashBookMapperIn cashBookMapperIn;

    @PostMapping(path = "/{id}")
    public ResponseEntity<CashbookDtoResponse> create(
        @PathVariable(name = "id") final UUID cashBookId, @RequestBody @Valid RegistrationCreateDtoRequest registrationCreateDtoRequest) {

        log.info("Controller Create iniciado para cashBookId: {} {}", cashBookId, registrationCreateDtoRequest);

        var response = Optional.ofNullable(registrationCreateDtoRequest)
                .map(registrationMapperIn::toRegistration)
                .map(registration -> registrationCreateInputPort.create(cashBookId, registration))
                .map(cashBookMapperIn::toCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller Create concluído: {}", response);

        return ResponseEntity
                .created(URI.create(URI_REGISTRATION + "/" + cashBookId))
                .body(response);
    }
}

