package com.nomad.accounting_analysis.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nomad.accounting_analysis.core.domain.enums.CostCenterEnum;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BalanceCashbookDtoResponse(

    BigDecimal annualSumCredits,

    BigDecimal annualSumDebts,

    BigDecimal annualBalance,


    Map<CostCenterEnum, BigDecimal> annualSumCreditsByCostCenter,

    Map<CostCenterEnum, BigDecimal> annualSumDebitsByCostCenter,


    Map<Month, BigDecimal>monthlySumCredits,

    Map<Month, BigDecimal> monthlySumDebits,

    Map<Month, BigDecimal> monthlyBalance
) { }

