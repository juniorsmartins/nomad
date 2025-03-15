package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.port.input.RegistrationDeleteInputPort;
import com.nomad.accounting.application.port.output.RegistrationDeleteOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationDeleteService implements RegistrationDeleteInputPort {

    private final RegistrationDeleteOutputPort registrationDeleteOutputPort;

    @Override
    public void delete(@NonNull final UUID registrationId) {

        log.info("Serviço delete iniciado com id: {}", registrationId);

        registrationDeleteOutputPort.delete(registrationId);

        log.info("Serviço delete concluído: {}", registrationId);
    }
}

