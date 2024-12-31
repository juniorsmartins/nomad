package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.application.core.domain.CashBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashBookMapperOut {

    CashbookEntity toCashBookEntity(CashBook cashBook);

    CashBook toCashBook(CashbookEntity cashBookEntity);
}

