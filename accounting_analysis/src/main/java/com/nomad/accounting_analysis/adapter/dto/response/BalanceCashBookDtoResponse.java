package com.nomad.accounting_analysis.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BalanceCashBookDtoResponse(

    BigDecimal annualSumCredits,

    BigDecimal annualSumDebts,

    BigDecimal annualBalance,

    Map<Month, BigDecimal>monthlySumCredits,

    Map<Month, BigDecimal> monthlySumDebits,

    Map<Month, BigDecimal> monthlyBalance
) { }

