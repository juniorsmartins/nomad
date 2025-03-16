package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.port.input.InvestmentDeleteInputPort;
import com.nomad.accounting.application.port.output.InvestmentDeleteOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvestmentDeleteService implements InvestmentDeleteInputPort {

    private final InvestmentDeleteOutputPort investmentDeleteOutputPort;

    @Override
    public void delete(@NonNull final UUID investmentId) {

        log.info("Serviço delete iniciado com id: {}", investmentId);

        investmentDeleteOutputPort.delete(investmentId);

        log.info("Serviço delete concluído: {}", investmentId);
    }
}

