package com.nomad.accounting_analysis.port.dto;

import com.nomad.accounting_analysis.core.domain.enums.CostCenterEnum;
import com.nomad.accounting_analysis.core.domain.enums.TypeOperationEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistrationDtoResponse(

        String description,

        BigDecimal amount,

        TypeOperationEnum typeOperationEnum,

        LocalDate dateOperation,

        CostCenterEnum costCenterEnum,

        String supplier
) { }

