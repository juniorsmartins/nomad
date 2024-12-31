package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.CashBookMapperOut;
import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.output.CashBookCreateOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashBookCreateAdapter implements CashBookCreateOutputPort {

    private final CashBookRepository cashBookRepository;

    private final CashBookMapperOut cashBookMapperOut;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public CashBook create(@NonNull final CashBook cashBook) {

        log.info("Adaptador Create iniciado: {}", cashBook);

        var cashBookSaved = Optional.of(cashBook)
                .map(cashBookMapperOut::toCashBookEntity)
                .map(cashBookRepository::save)
                .map(cashBookMapperOut::toCashBook)
                .orElseThrow();

        log.info("Adaptador Create concluído: {}", cashBookSaved);

        return cashBookSaved;
    }
}

