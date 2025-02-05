package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.*;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.application.core.domain.Cashbook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring", uses = {RegistrationMapperIn.class})
public interface CashbookMapperIn {


}

