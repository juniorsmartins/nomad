package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.*;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.adapter.entity.RegistrationEntity;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.core.domain.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CentralMapper {

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

    CashbookEntity toCashBookEntity(Cashbook cashBook);

    Cashbook toCashBook(CashbookEntity cashBookEntity);

    @Mapping(target = "registrationId", ignore = true)
    @Mapping(target = "cashbook", ignore = true)
    Registration toRegistration(RegistrationCreateDtoRequest registrationCreateDtoRequest);

    RegistrationFindDtoResponse toRegistrationFindDtoResponse(RegistrationEntity registrationEntity);

    @Mapping(source = "cashbook.cashbookId", target = "cashbookId")
    RegistrationDtoResponse toRegistrationDtoResponse(Registration registration);

    @Mapping(source = "cashbook.cashbookId", target = "cashbookId")
    RegistrationDtoResponse toRegistrationDtoResponse(RegistrationEntity registrationEntity);

    RegistrationSearchDtoResponse toRegistrationSearchDtoResponse(RegistrationEntity registrationEntity);

    RegistrationEntity toRegistrationEntity(Registration registration);

    Registration toRegistration(RegistrationEntity registrationEntity);
}

