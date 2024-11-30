package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.application.core.domain.CashBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashBookMapperOut {

    CashBookEntity toCashBookEntity(CashBook cashBook);

    CashBook toCashBook(CashBookEntity cashBookEntity);
}

