package com.nomad.accounting.application.core.domain;

import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.application.core.domain.enums.CostCenterEnum;
import com.nomad.accounting.application.core.domain.enums.TypeOperationEnum;
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

    private CashbookEntity cashbook;

    private String description;

    private BigDecimal amount;

    private TypeOperationEnum typeOperationEnum;

    private LocalDate dateOperation;

    private CostCenterEnum costCenterEnum;

    private String supplier;
}

