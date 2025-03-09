package com.nomad.accounting.adapter.dto.filter;

import jakarta.validation.Valid;

import java.time.Year;
import java.util.UUID;

public record CashbookFilter(

        UUID cashBookId,

        Year yearReference,

        String document,

        @Valid
        RegistrationFilter registration,

        @Valid
        InvestmentFilter investment
) { }

