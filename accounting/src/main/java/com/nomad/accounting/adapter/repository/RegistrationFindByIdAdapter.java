package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.RegistrationEntity;
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

    private final RegistrationRepository registrationRepository;

    @Transactional(readOnly = true)
    @Override
    public RegistrationEntity findById(@NonNull final UUID registrationId) {

        log.info("Adaptador findById iniciado: {}", registrationId);

        var registrationFind = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RegistrationNotFoundException(registrationId));

        log.info("Adaptador findById concluído: {}", registrationFind);

        return registrationFind;
    }
}

