package com.nomad.accounting.adapter.dto.in;

import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record RegistrationCreateDtoRequest(

        @NotNull
        UUID cashBookId,

        @NotBlank
        String description,

        @NotNull
        BigDecimal amount,

        @NotNull
        TypeOperation typeOperation,

        @NotNull
        CostCenter costCenter,

        @NotBlank
        String supplier)
{ }

