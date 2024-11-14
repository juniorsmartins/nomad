package com.nomad.accounting.adapter.dto.out;

import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;

import java.math.BigDecimal;
import java.util.UUID;

public record RegistrationDtoResponse(

        UUID cashBookId,

        String description,

        BigDecimal amount,

        TypeOperation typeOperation,

        CostCenter costCenter,

        String supplier)
{ }

