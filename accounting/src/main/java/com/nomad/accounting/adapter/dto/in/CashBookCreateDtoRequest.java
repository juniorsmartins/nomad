package com.nomad.accounting.adapter.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.Year;

import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_DOCUMENT;
import static com.nomad.accounting.config.AccountingConstants.MIN_CARACTER_DOCUMENT;

public record CashBookCreateDtoRequest(

        @NotNull
        Year yearReference,

        @NotBlank
        @Length(min = MIN_CARACTER_DOCUMENT, max = MAX_CARACTER_DOCUMENT)
        String document
) { }

