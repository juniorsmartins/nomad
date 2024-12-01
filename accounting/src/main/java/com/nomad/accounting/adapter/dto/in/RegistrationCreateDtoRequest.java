package com.nomad.accounting.adapter.dto.in;

import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_DESCRIPTION;
import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_SUPPLIER;

public record RegistrationCreateDtoRequest(

        @NotBlank
        @Length(max = MAX_CARACTER_DESCRIPTION)
        String description,

        @NotNull
        BigDecimal amount,

        @NotNull
        TypeOperation typeOperation,

        @NotNull
        LocalDate dateOperation,

        @NotNull
        CostCenter costCenter,

        @NotBlank
        @Length(max = MAX_CARACTER_SUPPLIER)
        String supplier
) { }

