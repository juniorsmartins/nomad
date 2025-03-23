package com.nomad.accounting_analysis.mapper;

import com.nomad.accounting_analysis.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.dto.response.BalanceCashbookDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashbookMapperWeb {

    BalanceCashbookDtoResponse toBalanceCashbookDtoResponse(BalanceCashbook balanceCashbook);
}

