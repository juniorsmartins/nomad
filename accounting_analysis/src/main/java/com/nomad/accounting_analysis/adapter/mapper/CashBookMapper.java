package com.nomad.accounting_analysis.adapter.mapper;

import com.nomad.accounting_analysis.adapter.dto.in.CashBookDtoResponse;
import com.nomad.accounting_analysis.adapter.dto.response.BalanceCashBookDtoResponse;
import com.nomad.accounting_analysis.application.core.domain.BalanceCashBook;
import com.nomad.accounting_analysis.application.core.domain.CashBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashBookMapper {

    CashBook toCashBook(CashBookDtoResponse cashBookDtoResponse);

    BalanceCashBookDtoResponse toBalanceCashBookDtoResponse(BalanceCashBook balanceCashBook);
}

