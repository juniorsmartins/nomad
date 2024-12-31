package com.nomad.accounting_analysis.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nomad.accounting_analysis.application.core.domain.enums.CostCenter;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BalanceCashbookDtoResponse(

    BigDecimal annualSumCredits,

    BigDecimal annualSumDebts,

    BigDecimal annualBalance,

    Map<CostCenter, BigDecimal> annualSumCreditsByCostCenter,

    Map<CostCenter, BigDecimal> annualSumDebitsByCostCenter,

    Map<Month, BigDecimal>monthlySumCredits,

    Map<Month, BigDecimal> monthlySumDebits,

    Map<Month, BigDecimal> monthlyBalance
) { }

