package com.nomad.accounting.adapter.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record CashBookCreateDtoRequest(

        @NotNull
        Year yearReference,

        @NotBlank
        String document
) { }

