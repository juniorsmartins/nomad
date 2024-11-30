package com.nomad.accounting.adapter.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.Year;
import java.util.UUID;

public record CashBookUpdateDtoRequest(

        @NotNull
        UUID cashBookId,

        @NotNull
        Year yearReference,

        @NotBlank
        @Length(min = 11, max = 14)
        String document
) { }

