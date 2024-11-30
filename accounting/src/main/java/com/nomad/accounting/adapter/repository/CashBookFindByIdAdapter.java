package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.out.CashBookMapperOut;
import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.output.CashBookFindByIdOutputPort;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashBookFindByIdAdapter implements CashBookFindByIdOutputPort {

    private final CashBookRepository cashBookRepository;

    private final CashBookMapperOut cashBookMapperOut;

    @Override
    public CashBook findById(@NotNull final UUID cashBookId) {

        log.info("Adaptador iniciado: {}", cashBookId);

        var cashBookFinded = cashBookRepository.findById(cashBookId)
                .map(cashBookMapperOut::toCashBook)
                .orElseThrow();

        log.info("Adaptador concluído: {}", cashBookFinded);

        return cashBookFinded;
    }
}

