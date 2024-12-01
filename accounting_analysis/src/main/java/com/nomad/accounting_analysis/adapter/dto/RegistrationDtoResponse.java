package com.nomad.accounting_analysis.adapter.dto;

import com.nomad.accounting_analysis.application.core.domain.enums.CostCenter;
import com.nomad.accounting_analysis.application.core.domain.enums.TypeOperation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistrationDtoResponse(

        String description,

        BigDecimal amount,

        TypeOperation typeOperation,

        LocalDate dateOperation,

        CostCenter costCenter,

        String supplier
) { }

