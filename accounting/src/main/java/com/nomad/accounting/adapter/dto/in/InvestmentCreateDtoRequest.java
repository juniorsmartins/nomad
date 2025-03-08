package com.nomad.accounting.adapter.dto.in;

import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvestmentCreateDtoRequest(

        String description,

        BigDecimal amount,

        TypeActionEnum typeActionEnum,

        LocalDate dateOperation,

        CategoryEnum categoryEnum,

        String supplier
) { }

