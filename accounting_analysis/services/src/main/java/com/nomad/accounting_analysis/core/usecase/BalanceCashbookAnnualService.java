package com.nomad.accounting_analysis.core.usecase;

import com.nomad.accounting_analysis.adapter.mapper.CashbookMapper;
import com.nomad.accounting_analysis.application.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.application.core.domain.Cashbook;
import com.nomad.accounting_analysis.application.port.input.BalanceCashbookInputPort;
import com.nomad.accounting_analysis.application.port.output.BalanceCashbookOutputPort;
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

    private final CashbookMapper cashbookMapper;

    @Override
    public BalanceCashbook annual(@NonNull final UUID id) {

        log.info("Serviço Annual iniciado: {}", id);

        var cashbookDtoResponse = balanceCashbookOutputPort.findById(id);
        var cashbook = cashbookMapper.toCashbook(cashbookDtoResponse);
        var balanceCashbook = generateCashbookBalance(cashbook);

        log.info("Serviço Annual concluído: {}", balanceCashbook);

        return balanceCashbook;
    }

    private BalanceCashbook generateCashbookBalance(@NonNull final Cashbook cashbook) {

        return BalanceCashbook.builder()
                .annualSumCredits(cashbook.annualSumCredits())
                .annualSumDebts(cashbook.annualSumDebits())
                .annualBalance(cashbook.annualBalance())
                .annualInvestment(cashbook.annualSumInvestment())
                .annualSumCreditsByCostCenter(cashbook.annualSumCreditsByCostCenter())
                .annualSumDebitsByCostCenter(cashbook.annualSumDebitsByCostCenter())
                .annualSumInvestmentByCostCenter(cashbook.annualSumInvestmentByCostCenter())
                .monthlySumCredits(cashbook.monthlySumCredits())
                .monthlySumDebits(cashbook.monthlySumDebits())
                .monthlyBalance(cashbook.monthlyBalance())
                .monthlyInvestment(cashbook.monthlyInvestment())
                .build();
    }
}

