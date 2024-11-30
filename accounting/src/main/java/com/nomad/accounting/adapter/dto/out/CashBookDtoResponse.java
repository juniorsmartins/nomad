package com.nomad.accounting.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Year;
import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CashBookDtoResponse(

        UUID cashBookId,

        Year yearReference,

        String document
) { }

