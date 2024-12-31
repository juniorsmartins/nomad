package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.*;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.application.core.domain.Cashbook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashbookMapperIn {

    @Mapping(target = "cashbookId", ignore = true)
    @Mapping(target = "registrations", ignore = true)
    Cashbook toCashBook(CashbookCreateDtoRequest cashBookCreateDtoRequest);

    @Mapping(target = "registrations", ignore = true)
    Cashbook toCashBook(CashbookUpdateDtoRequest cashBookUpdateDtoRequest);

    CashbookDtoResponse toCashBookDtoResponse(Cashbook cashBook);

    CashbookDtoResponse toCashBookDtoResponse(CashbookEntity cashBookEntity);

    CashbookCreateDtoResponse toCashBookCreateDtoResponse(Cashbook cashBook);

    CashbookUpdateDtoResponse toCashBookUpdateDtoResponse(Cashbook cashBook);

    CashbookFindDtoResponse toCashBookFindDtoResponse(CashbookEntity cashBookEntity);

    CashbookFindDtoResponse toCashBookFindDtoResponse(Cashbook cashBook);

    CashbookSearchDtoResponse toCashBookSearchDtoResponse(CashbookEntity cashBookEntity);
}

