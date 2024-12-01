package com.nomad.accounting_analysis.adapter.dto;

import java.time.Year;
import java.util.List;
import java.util.UUID;

public record CashBookDtoResponse(

        UUID cashBookId,

        Year yearReference,

        String document,

        List<RegistrationDtoResponse> registrations
) { }

