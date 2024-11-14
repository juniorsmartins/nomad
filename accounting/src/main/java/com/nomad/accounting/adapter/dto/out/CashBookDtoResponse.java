package com.nomad.accounting.adapter.dto.out;

import java.time.Year;

public record CashBookDtoResponse(

        Year yearReference,

        String document)
{ }

