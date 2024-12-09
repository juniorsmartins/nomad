package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.port.input.RegistrationDeleteInputPort;
import com.nomad.accounting.application.port.output.RegistrationDeleteOutputPort;
import com.nomad.accounting.application.port.output.RegistrationFindByIdOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationDeleteService implements RegistrationDeleteInputPort {

    private RegistrationFindByIdOutputPort registrationFindByIdOutputPort;

    private RegistrationDeleteOutputPort registrationDeleteOutputPort;

    @Override
    public void delete(@NonNull final UUID registrationId) {

        log.info("Serviço Delete iniciado com id: {}", registrationId);

        registrationFindByIdOutputPort.findById(registrationId);
        registrationDeleteOutputPort.delete(registrationId);

        log.info("Serviço Delete concluído: {}", registrationId);
    }
}

