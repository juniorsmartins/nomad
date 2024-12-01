package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.adapter.mapper.CashBookMapperOut;
import com.nomad.accounting.adapter.mapper.RegistrationMapperOut;
import com.nomad.accounting.application.core.domain.CashBook;
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

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RegistrationCreateAdapter implements RegistrationCreateOutputPort {

    private final CashBookRepository cashBookRepository;

    private final RegistrationMapperOut registrationMapperOut;

    private final CashBookMapperOut cashBookMapperOut;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public CashBook create(@NonNull UUID cashBookId, @NonNull final Registration registration) {

        log.info("Adaptador Create iniciado para cashBookId: {} {}", cashBookId, registration);

        var registrationCreated = cashBookRepository.findById(cashBookId)
                .map(cashBookEntity -> addRegistrationInCashBook(registration, cashBookEntity))
                .map(cashBookMapperOut::toCashBook)
                .orElseThrow(() -> new CashBookNotFoundException(cashBookId));

        log.info("Adaptador Create concluído: {}", registrationCreated);

        return registrationCreated;
    }

    private CashBookEntity addRegistrationInCashBook(Registration registration, CashBookEntity cashBookEntity) {
        var registrationVo = registrationMapperOut.toRegistrationVo(registration);
        cashBookEntity.getRegistrations().add(registrationVo);
        return cashBookEntity;
    }
}

