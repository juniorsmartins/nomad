package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.CashBookMapperOut;
import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.output.CashBookFindByIdOutputPort;
import com.nomad.accounting.config.exception.http404.CashBookNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashBookFindByIdAdapter implements CashBookFindByIdOutputPort {

    private final CashBookRepository cashBookRepository;

    private final CashBookMapperOut cashBookMapperOut;

    @Transactional(readOnly = true)
    @Override
    public CashBook findById(@NotNull final UUID cashBookId) {

        log.info("Adaptador FindById iniciado: {}", cashBookId);

        var cashBookFinded = cashBookRepository.findById(cashBookId)
                .map(cashBookMapperOut::toCashBook)
                .orElseThrow(() -> new CashBookNotFoundException(cashBookId));

        log.info("Adaptador FindById concluído: {}", cashBookFinded);

        return cashBookFinded;
    }
}

