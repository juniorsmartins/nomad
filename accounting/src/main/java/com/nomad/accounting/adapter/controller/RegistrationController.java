package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.RegistrationUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import com.nomad.accounting.adapter.mapper.CashBookMapperIn;
import com.nomad.accounting.adapter.mapper.RegistrationMapperIn;
import com.nomad.accounting.application.port.input.RegistrationCreateInputPort;
import com.nomad.accounting.application.port.input.RegistrationDeleteInputPort;
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

    private final RegistrationDeleteInputPort registrationDeleteInputPort;

    private final RegistrationMapperIn registrationMapperIn;

    private final CashBookMapperIn cashBookMapperIn;

    @PostMapping(path = "/{id}")
    public ResponseEntity<CashBookDtoResponse> create(
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CashBookDtoResponse> delete(
        @PathVariable(name = "id") final UUID cashBookId, @RequestBody @Valid RegistrationUpdateDtoRequest registrationUpdateDtoRequest) {

        log.info("Controller Update iniciado para cashBookId: {} {}", cashBookId, registrationUpdateDtoRequest);

        var response = Optional.ofNullable(registrationUpdateDtoRequest)
                .map(registrationMapperIn::toRegistration)
                .map(registration -> registrationDeleteInputPort.delete(cashBookId, registration))
                .map(cashBookMapperIn::toCashBookDtoResponse)
                .orElseThrow();

        log.info("Controller Update concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }
}

