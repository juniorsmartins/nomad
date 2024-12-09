package com.nomad.accounting.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegistrationDtoResponse(

        UUID registrationId,

        CashBookEntity cashbook,

        String description,

        BigDecimal amount,

        TypeOperation typeOperation,

        LocalDate dateOperation,

        CostCenter costCenter,

        String supplier
) { }

