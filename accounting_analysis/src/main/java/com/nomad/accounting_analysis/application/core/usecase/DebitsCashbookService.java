package com.nomad.accounting_analysis.application.core.usecase;

import com.nomad.accounting_analysis.adapter.mapper.CashbookMapper;
import com.nomad.accounting_analysis.application.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.application.core.domain.Cashbook;
import com.nomad.accounting_analysis.application.port.input.DebitsCashbookInputPort;
import com.nomad.accounting_analysis.application.port.output.BalanceCashbookOutputPort;
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

    private final CashbookMapper cashbookMapper;

    @Override
    public BalanceCashbook debits(@NonNull final UUID id) {

        log.info("Serviço Debits iniciado: {}", id);

        var cashbookDtoResponse = balanceCashbookOutputPort.findById(id);
        var cashbook = cashbookMapper.toCashbook(cashbookDtoResponse);
        var debitsCashbook = generateCashbookDebits(cashbook);

        log.info("Serviço Debits concluído: {}", id);

        return debitsCashbook;
    }

    private BalanceCashbook generateCashbookDebits(@NonNull final Cashbook cashbook) {

        return BalanceCashbook.builder()
                .annualSumDebts(cashbook.annualSumDebits())
                .monthlySumDebits(cashbook.monthlySumDebits())
                .build();
    }
}

