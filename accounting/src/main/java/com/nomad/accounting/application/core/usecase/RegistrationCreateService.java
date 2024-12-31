package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.Registration;
import com.nomad.accounting.application.port.input.RegistrationCreateInputPort;
import com.nomad.accounting.application.port.output.RegistrationCreateOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationCreateService implements RegistrationCreateInputPort {

    private final RegistrationCreateOutputPort registrationCreateOutputPort;

    @Override
    public Registration create(@NonNull final UUID cashBookId, @NonNull Registration registration) {

        log.info("Serviço Create iniciado para cashbookId: {} {}", cashBookId, registration);

        var registrationCreated = registrationCreateOutputPort.create(cashBookId, registration);

        log.info("Serviço Create concluído: {}", registrationCreated);

        return registrationCreated;
    }
}

