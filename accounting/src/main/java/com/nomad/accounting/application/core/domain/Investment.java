package com.nomad.accounting.application.core.domain;

import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"investmentId"})
public class Investment {

    private UUID investmentId;

    private Cashbook cashbook;

    private String description;

    private BigDecimal amount;

    private TypeActionEnum typeActionEnum;

    private LocalDate dateOperation;

    private CategoryEnum categoryEnum;

    private String supplier;
}

