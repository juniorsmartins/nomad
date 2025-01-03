package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.port.input.CashbookCreateInputPort;
import com.nomad.accounting.application.port.output.CashbookCreateOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashbookCreateService implements CashbookCreateInputPort {

    private final CashbookCreateOutputPort cashBookCreateOutputPort;

    @Override
    public Cashbook create(@NonNull final Cashbook cashBook) {

        log.info("Serviço Create iniciado: {}", cashBook);

        var cashBookCreated = Optional.of(cashBook)
                .map(cashBookCreateOutputPort::create)
                .orElseThrow();

        log.info("Serviço Create concluído: {}", cashBookCreated);

        return cashBookCreated;
    }
}

