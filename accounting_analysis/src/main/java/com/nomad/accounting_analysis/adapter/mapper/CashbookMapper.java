package com.nomad.accounting_analysis.adapter.mapper;

import com.nomad.accounting_analysis.adapter.dto.in.CashbookDtoResponse;
import com.nomad.accounting_analysis.adapter.dto.response.BalanceCashbookDtoResponse;
import com.nomad.accounting_analysis.application.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.application.core.domain.Cashbook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashbookMapper {

    Cashbook toCashbook(CashbookDtoResponse cashbookDtoResponse);

    BalanceCashbookDtoResponse toBalanceCashbookDtoResponse(BalanceCashbook balanceCashbook);
}

