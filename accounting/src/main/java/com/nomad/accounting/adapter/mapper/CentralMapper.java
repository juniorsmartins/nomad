package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.in.InvestmentCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.*;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.adapter.entity.InvestmentEntity;
import com.nomad.accounting.adapter.entity.RegistrationEntity;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.core.domain.Investment;
import com.nomad.accounting.application.core.domain.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CentralMapper {

    @Mapping(target = "cashbookId", ignore = true)
    @Mapping(target = "registrations", ignore = true)
    @Mapping(target = "investments", ignore = true)
    Cashbook toCashbook(CashbookCreateDtoRequest cashbookCreateDtoRequest);

    @Mapping(target = "registrations", ignore = true)
    @Mapping(target = "investments", ignore = true)
    Cashbook toCashbook(CashbookUpdateDtoRequest cashbookUpdateDtoRequest);

    CashbookDtoResponse toCashbookDtoResponse(Cashbook cashbook);

    CashbookDtoResponse toCashbookDtoResponse(CashbookEntity cashbookEntity);

    CashbookCreateDtoResponse toCashbookCreateDtoResponse(Cashbook cashbook);

    CashbookUpdateDtoResponse toCashbookUpdateDtoResponse(Cashbook cashbook);

    CashbookFindDtoResponse toCashbookFindDtoResponse(CashbookEntity cashbookEntity);

    CashbookFindDtoResponse toCashbookFindDtoResponse(Cashbook cashbook);

    CashbookSearchDtoResponse toCashbookSearchDtoResponse(CashbookEntity cashbookEntity);

    CashbookEntity toCashbookEntity(Cashbook cashBook);

    Cashbook toCashbook(CashbookEntity cashBookEntity);

    @Mapping(target = "registrationId", ignore = true)
    @Mapping(target = "cashbook", ignore = true)
    Registration toRegistration(RegistrationCreateDtoRequest registrationCreateDtoRequest);

    Registration toRegistration(RegistrationEntity registrationEntity);

    RegistrationFindDtoResponse toRegistrationFindDtoResponse(RegistrationEntity registrationEntity);

    @Mapping(source = "cashbook.cashbookId", target = "cashbookId")
    RegistrationDtoResponse toRegistrationDtoResponse(Registration registration);

    @Mapping(source = "cashbook.cashbookId", target = "cashbookId")
    RegistrationDtoResponse toRegistrationDtoResponse(RegistrationEntity registrationEntity);

    RegistrationSearchDtoResponse toRegistrationSearchDtoResponse(RegistrationEntity registrationEntity);

    RegistrationEntity toRegistrationEntity(Registration registration);

    @Mapping(source = "cashbook.cashbookId", target = "cashbookId")
    InvestmentDtoResponse toInvestmentDtoResponse(Investment investment);

    @Mapping(source = "cashbook.cashbookId", target = "cashbookId")
    InvestmentDtoResponse toInvestmentDtoResponse(InvestmentEntity investmentEntity);

    InvestmentEntity toInvestmentEntity(Investment investment);

    Investment toInvestment(InvestmentEntity investmentEntity);

    Investment toInvestment(InvestmentCreateDtoRequest investmentCreateDtoRequest);

    InvestmentFindDtoResponse toInvestmentFindDtoResponse(InvestmentEntity investmentEntity);

    InvestmentSearchDtoResponse toInvestmentSearchDtoResponse(InvestmentEntity investmentEntity);
}

