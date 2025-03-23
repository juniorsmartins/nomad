package com.nomad.accounting_analysis.core.domain;

import com.nomad.accounting_analysis.core.domain.enums.CostCenterEnum;
import com.nomad.accounting_analysis.core.domain.enums.TypeOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class Registration {

    private UUID registrationId;

    private String description;

    private BigDecimal amount;

    private TypeOperationEnum typeOperationEnum;

    private LocalDate dateOperation;

    private CostCenterEnum costCenterEnum;

    private String supplier;
}

