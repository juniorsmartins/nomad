package com.nomad.accounting_analysis.application.core.usecase;

import com.nomad.accounting_analysis.adapter.mapper.CashBookMapper;
import com.nomad.accounting_analysis.application.core.domain.BalanceCashBook;
import com.nomad.accounting_analysis.application.core.domain.CashBook;
import com.nomad.accounting_analysis.application.port.input.BalanceCashBookInputPort;
import com.nomad.accounting_analysis.application.port.output.BalanceCashBookOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceCashBookAnnualService implements BalanceCashBookInputPort {

    private final BalanceCashBookOutputPort balanceCashBookOutputPort;

    private final CashBookMapper cashBookMapper;

    @Override
    public BalanceCashBook annual(@NonNull final UUID id) {

        log.info("Serviço Annual iniciado: {}", id);

        var cashBookDtoResponse = balanceCashBookOutputPort.findById(id);
        var cashBook = cashBookMapper.toCashBook(cashBookDtoResponse);
        var balanceCashBook = generateCashBookBalance(cashBook);

        log.info("Serviço Annual concluído: {}", balanceCashBook);

        return balanceCashBook;
    }

    private BalanceCashBook generateCashBookBalance(@NonNull final CashBook cashBook) {

        return BalanceCashBook.builder()
                .annualSumCredits(cashBook.annualSumCredits())
                .annualSumDebts(cashBook.annualSumDebits())
                .annualBalance(cashBook.annualBalance())
                .monthlySumCredits(cashBook.monthlySumCredits())
                .monthlySumDebits(cashBook.monthlySumDebits())
                .monthlyBalance(cashBook.monthlyBalance())
                .build();
    }
}

