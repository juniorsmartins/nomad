package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashBookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.application.core.domain.CashBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashBookMapperIn {

    CashBook toCashBook(CashBookCreateDtoRequest cashBookCreateDtoRequest);

    CashBook toCashBook(CashBookUpdateDtoRequest cashBookUpdateDtoRequest);

    CashBookDtoResponse toCashBookDtoResponse(CashBook cashBook);

    CashBookDtoResponse toCashBookDtoResponse(CashBookEntity cashBookEntity);
}

