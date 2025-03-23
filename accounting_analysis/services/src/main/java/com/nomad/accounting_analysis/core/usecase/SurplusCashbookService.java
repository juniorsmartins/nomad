package com.nomad.accounting_analysis.core.usecase;

import com.nomad.accounting_analysis.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.core.domain.Cashbook;
import com.nomad.accounting_analysis.core.mapper.CashbookMapperServices;
import com.nomad.accounting_analysis.port.input.SurplusCashbookInputPort;
import com.nomad.accounting_analysis.port.output.BalanceCashbookOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurplusCashbookService implements SurplusCashbookInputPort {

    private final BalanceCashbookOutputPort balanceCashbookOutputPort;

    private final CashbookMapperServices cashbookMapperServices;

    @Override
    public BalanceCashbook surplus(@NonNull final UUID id) {

        log.info("Serviço Surplus iniciado: {}", id);

        var cashbookDtoResponse = balanceCashbookOutputPort.findById(id);
        var cashbook = cashbookMapperServices.toCashbook(cashbookDtoResponse);
        var surplusCashbook = generateCashbookSurplus(cashbook);

        log.info("Serviço Surplus concluído: {}", surplusCashbook);

        return surplusCashbook;
    }

    private BalanceCashbook generateCashbookSurplus(@NonNull final Cashbook cashbook) {

        return BalanceCashbook.builder()
                .annualBalance(cashbook.annualBalance())
                .monthlyBalance(cashbook.monthlyBalance())
                .build();
    }
}

