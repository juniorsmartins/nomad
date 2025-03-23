package com.nomad.accounting_analysis.core.usecase;

import com.nomad.accounting_analysis.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.core.domain.Cashbook;
import com.nomad.accounting_analysis.core.mapper.CashbookMapperServices;
import com.nomad.accounting_analysis.port.input.DebitsCashbookInputPort;
import com.nomad.accounting_analysis.port.output.BalanceCashbookOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebitsCashbookService implements DebitsCashbookInputPort {

    private final BalanceCashbookOutputPort balanceCashbookOutputPort;

    private final CashbookMapperServices cashbookMapperServices;

    @Override
    public BalanceCashbook debits(@NonNull final UUID id) {

        log.info("Serviço debits iniciado: {}", id);

        var cashbookDtoResponse = balanceCashbookOutputPort.findById(id);
        var cashbook = cashbookMapperServices.toCashbook(cashbookDtoResponse);
        var debitsCashbook = generateCashbookDebits(cashbook);

        log.info("Serviço debits concluído: {}", id);

        return debitsCashbook;
    }

    private BalanceCashbook generateCashbookDebits(@NonNull final Cashbook cashbook) {

        return BalanceCashbook.builder()
                .annualSumDebts(cashbook.annualSumDebits())
                .monthlySumDebits(cashbook.monthlySumDebits())
                .build();
    }
}

