package com.nomad.accounting.application.core.domain;

import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"registrationId"})
public class Registration {

    private UUID registrationId;

    private CashBookEntity cashbook;

    private String description;

    private BigDecimal amount;

    private TypeOperation typeOperation;

    private LocalDate dateOperation;

    private CostCenter costCenter;

    private String supplier;
}

