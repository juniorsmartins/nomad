package com.nomad.accounting_analysis.core.usecase;

import com.nomad.accounting_analysis.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.core.domain.Cashbook;
import com.nomad.accounting_analysis.core.mapper.CashbookMapperServices;
import com.nomad.accounting_analysis.port.input.BalanceCashbookInputPort;
import com.nomad.accounting_analysis.port.output.BalanceCashbookOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceCashbookAnnualService implements BalanceCashbookInputPort {

    private final BalanceCashbookOutputPort balanceCashbookOutputPort;

    private final CashbookMapperServices cashbookMapperServices;

    @Override
    public BalanceCashbook annual(@NonNull final UUID id) {

        log.info("Serviço annual iniciado: {}", id);

        var cashbookDtoResponse = balanceCashbookOutputPort.findById(id);
        var cashbook = cashbookMapperServices.toCashbook(cashbookDtoResponse);
        var balanceCashbook = generateCashbookBalance(cashbook);

        log.info("Serviço annual concluído: {}", balanceCashbook);

        return balanceCashbook;
    }

    private BalanceCashbook generateCashbookBalance(@NonNull final Cashbook cashbook) {

        return BalanceCashbook.builder()
                .annualSumCredits(cashbook.annualSumCredits())
                .annualSumDebts(cashbook.annualSumDebits())
                .annualBalance(cashbook.annualBalance())
                .annualSumCreditsByCostCenter(cashbook.annualSumCreditsByCostCenter())
                .annualSumDebitsByCostCenter(cashbook.annualSumDebitsByCostCenter())
                .monthlySumCredits(cashbook.monthlySumCredits())
                .monthlySumDebits(cashbook.monthlySumDebits())
                .monthlyBalance(cashbook.monthlyBalance())
                .build();
    }
}

