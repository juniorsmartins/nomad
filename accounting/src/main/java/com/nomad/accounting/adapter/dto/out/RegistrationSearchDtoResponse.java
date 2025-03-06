package com.nomad.accounting.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nomad.accounting.application.core.domain.enums.CostCenterEnum;
import com.nomad.accounting.application.core.domain.enums.TypeOperationEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegistrationSearchDtoResponse(

        UUID registrationId,

        String description,

        BigDecimal amount,

        TypeOperationEnum typeOperationEnum,

        LocalDate dateOperation,

        CostCenterEnum costCenterEnum,

        String supplier
) { }

