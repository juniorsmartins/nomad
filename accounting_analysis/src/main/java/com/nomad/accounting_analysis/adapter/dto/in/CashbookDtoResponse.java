package com.nomad.accounting_analysis.adapter.dto.in;

import java.time.Year;
import java.util.List;
import java.util.UUID;

public record CashbookDtoResponse(

        UUID cashbookId,

        Year yearReference,

        String document,

        List<RegistrationDtoResponse> registrations
) { }

