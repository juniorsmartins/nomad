package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.port.output.CashbookUpdadeOutputPort;
import com.nomad.accounting.config.exception.http404.CashbookNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashbookUpdateAdapter implements CashbookUpdadeOutputPort {

    private final CashbookRepository cashbookRepository;

    private final CentralMapper centralMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Cashbook update(@NonNull Cashbook cashBook) {

        log.info("Adaptador Update iniciado: {}", cashBook);

        var cashBookId = cashBook.getCashbookId();

        var cashBookUpdated = cashbookRepository.findById(cashBookId)
                .map(entity -> {
                    BeanUtils.copyProperties(cashBook, entity, "cashbookId");
                    return entity;
                })
                .map(centralMapper::toCashbook)
                .orElseThrow(() -> new CashbookNotFoundException(cashBookId));

        log.info("Adaptador Update concluído: {}", cashBookUpdated);

        return cashBookUpdated;
    }
}

