package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.out.CashBookMapperOut;
import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.output.CashBookUpdadeOutputPort;
import com.nomad.accounting.config.exception.http404.CashBookNotFoundException;
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
public class CashBookUpdateAdapter implements CashBookUpdadeOutputPort {

    private final CashBookRepository cashBookRepository;

    private final CashBookMapperOut cashBookMapperOut;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public CashBook update(@NonNull CashBook cashBook) {

        log.info("Adaptador iniciado: {}", cashBook);

        var cashBookId = cashBook.getCashBookId();

        var cashBookUpdated = cashBookRepository.findById(cashBookId)
                .map(entity -> {
                    BeanUtils.copyProperties(cashBook, entity, "cashBookId");
                    return entity;
                })
                .map(cashBookMapperOut::toCashBook)
                .orElseThrow(() -> new CashBookNotFoundException(cashBookId));

        log.info("Adaptador concluído: {}", cashBookUpdated);

        return cashBookUpdated;
    }
}

