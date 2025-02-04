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
    Cashbook toCashbook(CashbookCreateDtoRequest cashbookCreateDtoRequest);

    @Mapping(target = "registrations", ignore = true)
    Cashbook toCashbook(CashbookUpdateDtoRequest cashbookUpdateDtoRequest);

    CashbookDtoResponse toCashbookDtoResponse(Cashbook cashbook);

    CashbookDtoResponse toCashbookDtoResponse(CashbookEntity cashbookEntity);

    CashbookCreateDtoResponse toCashbookCreateDtoResponse(Cashbook cashbook);

    CashbookUpdateDtoResponse toCashbookUpdateDtoResponse(Cashbook cashbook);

    CashbookFindDtoResponse toCashbookFindDtoResponse(CashbookEntity cashbookEntity);

    CashbookFindDtoResponse toCashbookFindDtoResponse(Cashbook cashbook);

    CashbookSearchDtoResponse toCashbookSearchDtoResponse(CashbookEntity cashbookEntity);
}

