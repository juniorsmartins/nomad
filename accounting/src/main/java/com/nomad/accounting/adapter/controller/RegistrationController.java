package com.nomad.accounting.adapter.controller;

import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.RegistrationDtoResponse;
import com.nomad.accounting.adapter.dto.out.RegistrationFindDtoResponse;
import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.port.input.RegistrationCreateInputPort;
import com.nomad.accounting.application.port.input.RegistrationDeleteInputPort;
import com.nomad.accounting.application.port.output.RegistrationFindByIdOutputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = {RegistrationController.URI_REGISTRATION})
@RequiredArgsConstructor
public class RegistrationController {

    protected static final String URI_REGISTRATION = "/api/v1/accounting/registration";

    private final RegistrationCreateInputPort registrationCreateInputPort;

    private final RegistrationFindByIdOutputPort registrationFindByIdOutputPort;

    private final RegistrationDeleteInputPort registrationDeleteInputPort;

    private final CentralMapper centralMapper;

    @PostMapping(path = "/{id}")
    public ResponseEntity<RegistrationDtoResponse> create(
            @PathVariable(name = "id") final UUID cashBookId,
            @RequestBody @Valid RegistrationCreateDtoRequest registrationCreateDtoRequest) {

        log.info("Controller create iniciado para cashbookId: {} {}", cashBookId, registrationCreateDtoRequest);

        var response = Optional.ofNullable(registrationCreateDtoRequest)
                .map(centralMapper::toRegistration)
                .map(registration -> registrationCreateInputPort.create(cashBookId, registration))
                .map(centralMapper::toRegistrationDtoResponse)
                .orElseThrow();

        log.info("Controller create concluído: {}", response);

        return ResponseEntity
                .created(URI.create(URI_REGISTRATION + "/" + cashBookId))
                .body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RegistrationFindDtoResponse> findById(@PathVariable(name = "id") final UUID id) {

        log.info("Controller findById iniciado: {}", id);

        var response = Optional.of(id)
                .map(registrationFindByIdOutputPort::findById)
                .map(centralMapper::toRegistrationFindDtoResponse)
                .orElseThrow();

        log.info("Controller findById concluído: {}", response);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") final UUID registrationId) {

        log.info("Controller update iniciado para registrationId: {}", registrationId);

        Optional.ofNullable(registrationId)
                .ifPresentOrElse(registrationDeleteInputPort::delete,
                        () -> {
                            throw new NoSuchElementException();
                        }
                );

        log.info("Controller update concluído: {}", registrationId);

        return ResponseEntity
                .noContent()
                .build();
    }
}

