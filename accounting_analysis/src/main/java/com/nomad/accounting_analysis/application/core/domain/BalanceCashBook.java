package com.nomad.accounting_analysis.application.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@Builder
@Getter
@Setter
public final class BalanceCashBook {

    // Total crédito anual
    private BigDecimal annualSumCredits;

    // Total débito anual
    private BigDecimal annualSumDebts;

    // Total saldo anual
    private BigDecimal annualBalance;

    // Total crédito mês
    private Map<Month, BigDecimal> monthlySumCredits;

    // Total débito mês
    private Map<Month, BigDecimal> monthlySumDebits;

    // Total saldo mês
    private Map<Month, BigDecimal> monthlyBalance;
}

