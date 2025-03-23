package com.nomad.accounting_analysis.core.mapper;

import com.nomad.accounting_analysis.core.domain.Registration;
import com.nomad.accounting_analysis.port.dto.CashbookDtoResponse;
import com.nomad.accounting_analysis.core.domain.Cashbook;
import com.nomad.accounting_analysis.port.dto.RegistrationDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashbookMapperServices {

    Cashbook toCashbook(CashbookDtoResponse cashbookDtoResponse);

    @Mapping(target = "registrationId", ignore = true)
    Registration toRegistration(RegistrationDtoResponse registrationDtoResponse);
}

