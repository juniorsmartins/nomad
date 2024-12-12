package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashBookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashBookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashBookCreateDtoResponse;
import com.nomad.accounting.adapter.dto.out.CashBookDtoResponse;
import com.nomad.accounting.adapter.dto.out.CashBookFindDtoResponse;
import com.nomad.accounting.adapter.dto.out.CashBookUpdateDtoResponse;
import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.application.core.domain.CashBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashBookMapperIn {

    @Mapping(target = "cashbookId", ignore = true)
    @Mapping(target = "registrations", ignore = true)
    CashBook toCashBook(CashBookCreateDtoRequest cashBookCreateDtoRequest);

    @Mapping(target = "registrations", ignore = true)
    CashBook toCashBook(CashBookUpdateDtoRequest cashBookUpdateDtoRequest);

    CashBookDtoResponse toCashBookDtoResponse(CashBook cashBook);

    CashBookDtoResponse toCashBookDtoResponse(CashBookEntity cashBookEntity);

    CashBookCreateDtoResponse toCashBookCreateDtoResponse(CashBook cashBook);

    CashBookUpdateDtoResponse toCashBookUpdateDtoResponse(CashBook cashBook);

    CashBookFindDtoResponse toCashBookFindDtoResponse(CashBookEntity cashBookEntity);

    CashBookFindDtoResponse toCashBookFindDtoResponse(CashBook cashBook);
}

