package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.port.input.CashbookUpdateInputPort;
import com.nomad.accounting.application.port.output.CashbookUpdadeOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashbookUpdateService implements CashbookUpdateInputPort {

    private final CashbookUpdadeOutputPort cashBookUpdadeOutputPort;

    @Override
    public Cashbook update(@NonNull Cashbook cashBook) {

        log.info("Serviço Update iniciado: {}", cashBook);

        var cashBookUpdated = Optional.of(cashBook)
                .map(cashBookUpdadeOutputPort::update)
                .orElseThrow();

        log.info("Serviço Update concluído: {}", cashBookUpdated);

        return cashBookUpdated;
    }
}

