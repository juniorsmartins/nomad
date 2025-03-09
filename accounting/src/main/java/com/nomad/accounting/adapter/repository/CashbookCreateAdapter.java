package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.port.output.CashbookCreateOutputPort;
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
public class CashbookCreateAdapter implements CashbookCreateOutputPort {

    private final CashbookRepository cashbookRepository;

    private final CentralMapper centralMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Cashbook create(@NonNull final Cashbook cashBook) {

        log.info("Adaptador Create iniciado: {}", cashBook);

        var cashBookSaved = Optional.of(cashBook)
                .map(centralMapper::toCashbookEntity)
                .map(cashbookRepository::save)
                .map(centralMapper::toCashbook)
                .orElseThrow();

        log.info("Adaptador Create concluído: {}", cashBookSaved);

        return cashBookSaved;
    }
}

