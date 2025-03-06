package com.nomad.accounting.adapter.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InvestmentDtoResponse(

        UUID investmentId,

        UUID cashbookId,

        String description,

        BigDecimal amount,

        TypeActionEnum typeActionEnum,

        LocalDate dateOperation,

        CategoryEnum categoryEnum,

        String supplier
) { }

