package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.port.output.CashbookFindByIdOutputPort;
import com.nomad.accounting.config.exception.http404.CashbookNotFoundException;
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

    private final CashbookRepository cashbookRepository;

    private final CentralMapper centralMapper;

    @Transactional(readOnly = true)
    @Override
    public Cashbook findById(@NotNull final UUID cashbookId) {

        log.info("Adaptador findById iniciado: {}", cashbookId);

        var cashbookFinded = cashbookRepository.findById(cashbookId)
                .map(centralMapper::toCashbook)
                .orElseThrow(() -> new CashbookNotFoundException(cashbookId));

        log.info("Adaptador findById concluído: {}", cashbookFinded);

        return cashbookFinded;
    }
}

