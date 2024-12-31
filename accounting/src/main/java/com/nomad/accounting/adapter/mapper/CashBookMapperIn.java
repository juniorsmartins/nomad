package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookDtoResponse;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.application.core.domain.CashBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashBookMapperIn {

    @Mapping(target = "cashBookId", ignore = true)
    @Mapping(target = "registrations", ignore = true)
    CashBook toCashBook(CashbookCreateDtoRequest cashBookCreateDtoRequest);

    @Mapping(target = "registrations", ignore = true)
    CashBook toCashBook(CashbookUpdateDtoRequest cashBookUpdateDtoRequest);

    CashbookDtoResponse toCashBookDtoResponse(CashBook cashBook);

    CashbookDtoResponse toCashBookDtoResponse(CashbookEntity cashBookEntity);
}

