package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.RegistrationEntity;
import com.nomad.accounting.adapter.mapper.RegistrationMapperOut;
import com.nomad.accounting.application.core.domain.Registration;
import com.nomad.accounting.application.port.output.RegistrationCreateOutputPort;
import com.nomad.accounting.config.exception.http404.CashBookNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RegistrationCreateAdapter implements RegistrationCreateOutputPort {

    private final CashBookRepository cashBookRepository;

    private final RegistrationRepositoy registrationRepositoy;

    private final RegistrationMapperOut registrationMapperOut;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Registration create(@NonNull final UUID cashbookId, @NonNull final Registration registration) {

        log.info("Adaptador Create iniciado para cashbookId: {} {}", cashbookId, registration);

        var registrationCreated = Optional.of(registration)
                .map(registrationMapperOut::toRegistrationEntity)
                .map(entity -> registrationRepositoy.save(verifyCashBook(cashbookId, entity)))
                .map(registrationMapperOut::toRegistration)
                .orElseThrow();

        log.info("Adaptador Create concluído: {}", registrationCreated);

        return registrationCreated;
    }

    private RegistrationEntity verifyCashBook(UUID cashbookId, RegistrationEntity registrationEntity) {
        return cashBookRepository.findById(cashbookId)
                .map(cashBookEntity -> {
                    registrationEntity.setCashbook(cashBookEntity);
                    return registrationEntity;
                })
            .orElseThrow(() -> new CashBookNotFoundException(cashbookId));
    }
}

