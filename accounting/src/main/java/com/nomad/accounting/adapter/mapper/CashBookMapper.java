package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import com.nomad.accounting.application.core.domain.CashBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashBookMapper {

    CashBook toCashBook(CashBookCreateDtoRequest cashBookCreateDtoRequest);

    CashBookDtoResponse toCashBookDtoResponse(CashBook cashBook);
}

