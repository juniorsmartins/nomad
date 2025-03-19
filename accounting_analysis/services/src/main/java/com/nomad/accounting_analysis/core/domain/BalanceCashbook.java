package com.nomad.accounting_analysis.core.domain;

import com.nomad.accounting_analysis.application.core.domain.enums.CostCenter;
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

    // Total investido
    private BigDecimal annualInvestment;


    // Total anual de créditos por CostCenter
    private Map<CostCenter, BigDecimal> annualSumCreditsByCostCenter;

    // Total anual de débitos por CostCenter
    private Map<CostCenter, BigDecimal> annualSumDebitsByCostCenter;

    // Total anual investido por CostCenter
    private Map<CostCenter, BigDecimal> annualSumInvestmentByCostCenter;


    // Total crédito mês
    private Map<Month, BigDecimal> monthlySumCredits;

    // Total débito mês
    private Map<Month, BigDecimal> monthlySumDebits;

    // Total saldo mês
    private Map<Month, BigDecimal> monthlyBalance;

    // Total investido mês
    private Map<Month, BigDecimal> monthlyInvestment;
}

