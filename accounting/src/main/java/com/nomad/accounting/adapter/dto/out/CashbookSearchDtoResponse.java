package com.nomad.accounting.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record CashbookSearchDtoResponse(

        UUID cashbookId,

        Year yearReference,

        String document,

        List<RegistrationSearchDtoResponse> registrations,

        List<InvestmentSearchDtoResponse> investments
) { }

