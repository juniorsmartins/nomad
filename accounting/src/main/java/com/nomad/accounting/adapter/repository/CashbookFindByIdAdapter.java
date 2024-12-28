package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.CashbookMapperOut;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.port.output.CashbookFindByIdOutputPort;
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
public class CashbookFindByIdAdapter implements CashbookFindByIdOutputPort {

    private final CashbookRepository cashBookRepository;

    private final CashbookMapperOut cashBookMapperOut;

    @Transactional(readOnly = true)
    @Override
    public Cashbook findById(@NotNull final UUID cashBookId) {

        log.info("Adaptador FindById iniciado: {}", cashBookId);

        var cashBookFinded = cashBookRepository.findById(cashBookId)
                .map(cashBookMapperOut::toCashBook)
                .orElseThrow(() -> new CashBookNotFoundException(cashBookId));

        log.info("Adaptador FindById concluído: {}", cashBookFinded);

        return cashBookFinded;
    }
}

