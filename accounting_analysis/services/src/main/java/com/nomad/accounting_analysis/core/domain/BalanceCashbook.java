package com.nomad.accounting_analysis.core.domain;

import com.nomad.accounting_analysis.core.domain.enums.CostCenterEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@Builder
@Getter
@Setter
public final class BalanceCashbook {

    // Total crédito anual
    private BigDecimal annualSumCredits;

    // Total débito anual
    private BigDecimal annualSumDebts;

    // Total saldo anual
    private BigDecimal annualBalance;


    // Total anual de créditos por CostCenter
    private Map<CostCenterEnum, BigDecimal> annualSumCreditsByCostCenter;

    // Total anual de débitos por CostCenter
    private Map<CostCenterEnum, BigDecimal> annualSumDebitsByCostCenter;


    // Total crédito mês
    private Map<Month, BigDecimal> monthlySumCredits;

    // Total débito mês
    private Map<Month, BigDecimal> monthlySumDebits;

    // Total saldo mês
    private Map<Month, BigDecimal> monthlyBalance;
}

