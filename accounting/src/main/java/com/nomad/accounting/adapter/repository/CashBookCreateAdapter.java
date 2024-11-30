package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.mapper.out.CashBookMapperOut;
import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.output.CashBookCreateOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashBookCreateAdapter implements CashBookCreateOutputPort {

    private final CashBookRepository cashBookRepository;

    private final CashBookMapperOut cashBookMapperOut;

    @Override
    public CashBook save(@NonNull final CashBook cashBook) {

        log.info("Adaptador iniciado: {}", cashBook);

        var cashBookSaved = Optional.of(cashBook)
                .map(cashBookMapperOut::toCashBookEntity)
                .map(cashBookRepository::save)
                .map(cashBookMapperOut::toCashBook)
                .orElseThrow();

        log.info("Adaptador concluído: {}", cashBookSaved);

        return cashBookSaved;
    }
}

