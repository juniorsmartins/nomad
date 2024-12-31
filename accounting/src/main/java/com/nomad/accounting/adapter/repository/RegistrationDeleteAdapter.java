package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.RegistrationEntity;
import com.nomad.accounting.application.port.output.RegistrationDeleteOutputPort;
import com.nomad.accounting.config.exception.http404.RegistrationNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RegistrationDeleteAdapter implements RegistrationDeleteOutputPort {

    private final RegistrationRepository registrationRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Override
    public void delete(@NonNull final UUID registrationId) {

        log.info("Adapter Delete iniciado: {}", registrationId);

        var cashbookEntity = registrationRepository.findById(registrationId)
                .map(RegistrationEntity::getCashbook)
                .orElseThrow(() -> new RegistrationNotFoundException(registrationId));

        cashbookEntity.getRegistrations()
                .removeIf(registration -> registration.getRegistrationId().equals(registrationId));

        registrationRepository.deleteById(registrationId);

        log.info("Adapter Delete concluído: {}", registrationId);
    }
}

