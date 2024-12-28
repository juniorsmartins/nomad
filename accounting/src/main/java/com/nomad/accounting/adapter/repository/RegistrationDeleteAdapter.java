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

    private final CashbookRepository cashbookRepository;

    private final RegistrationRepositoy registrationRepositoy;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Override
    public void delete(@NonNull final UUID registrationId) {

        log.info("Adapter Delete iniciado: {}", registrationId);

        registrationRepositoy.findById(registrationId)
            .map(this::deleteFromCashbook)
            .map(registration -> {
                registrationRepositoy.delete(registration);
                return true;
            })
            .orElseThrow(() -> new RegistrationNotFoundException(registrationId));

        log.info("Adapter Delete concluído: {}", registrationId);
    }

    private RegistrationEntity deleteFromCashbook(RegistrationEntity registrationEntity) {
        registrationEntity.setCashbook(null);
        return registrationEntity;
    }
}

