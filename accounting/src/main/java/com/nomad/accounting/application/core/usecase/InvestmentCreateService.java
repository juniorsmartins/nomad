package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.Investment;
import com.nomad.accounting.application.port.input.InvestmentCreateInputPort;
import com.nomad.accounting.application.port.output.InvestmentCreateOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvestmentCreateService implements InvestmentCreateInputPort {

    private final InvestmentCreateOutputPort investmentCreateOutputPort;

    @Override
    public Investment create(@NonNull final UUID cashBookId, @NonNull Investment investment) {

        log.info("Serviço create iniciado para cashbookId: {} {}", cashBookId, investment);

        var investmentCreated = investmentCreateOutputPort.create(cashBookId, investment);

        log.info("Serviço create concluído: {}", investmentCreated);

        return investmentCreated;
    }
}

