package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.RegistrationMapperOut;
import com.nomad.accounting.application.core.domain.Registration;
import com.nomad.accounting.application.port.output.RegistrationFindByIdOutputPort;
import com.nomad.accounting.config.exception.http404.RegistrationNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RegistrationFindByIdAdapter implements RegistrationFindByIdOutputPort {

    private final RegistrationRepositoy registrationRepositoy;

    private final RegistrationMapperOut registrationMapperOut;

    @Transactional(readOnly = true)
    @Override
    public Registration findById(@NonNull final UUID registrationId) {

        log.info("Adaptador FindById iniciado: {}", registrationId);

        var registrationFind = registrationRepositoy.findById(registrationId)
                .map(registrationMapperOut::toRegistration)
                .orElseThrow(() -> new RegistrationNotFoundException(registrationId));

        log.info("Adaptador FindById concluído: {}", registrationFind);

        return registrationFind;
    }
}

