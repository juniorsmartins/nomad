package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.input.CashBookUpdateInputPort;
import com.nomad.accounting.application.port.output.CashBookUpdadeOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashBookUpdateService implements CashBookUpdateInputPort {

    private final CashBookUpdadeOutputPort cashBookUpdadeOutputPort;

    @Override
    public CashBook update(@NonNull CashBook cashBook) {

        log.info("Serviço Update iniciado: {}", cashBook);

        var cashBookUpdated = Optional.of(cashBook)
                .map(cashBookUpdadeOutputPort::update)
                .orElseThrow();

        log.info("Serviço Update concluído: {}", cashBookUpdated);

        return cashBookUpdated;
    }
}

