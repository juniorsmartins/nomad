package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.input.CashBookCreateInputPort;
import com.nomad.accounting.application.port.output.CashBookCreateOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashBookCreateService implements CashBookCreateInputPort {

    private final CashBookCreateOutputPort cashBookCreateOutputPort;

    @Override
    public CashBook create(@NonNull CashBook cashBook) {

        log.info("Serviço iniciado: {}", cashBook);

        var cashBookCreated = Optional.of(cashBook)
                .map(cashBookCreateOutputPort::save)
                .orElseThrow();

        log.info("Serviço concluído: {}", cashBookCreated);

        return cashBookCreated;
    }
}

