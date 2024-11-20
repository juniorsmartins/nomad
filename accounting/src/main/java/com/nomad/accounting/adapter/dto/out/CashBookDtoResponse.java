package com.nomad.accounting.adapter.dto.out;

import lombok.Builder;

import java.time.Year;

@Builder
public record CashBookDtoResponse(

        Year yearReference,

        String document)
{ }

