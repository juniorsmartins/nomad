package com.nomad.accounting_analysis.application.core.domain;

import com.nomad.accounting_analysis.application.core.domain.enums.CostCenter;
import com.nomad.accounting_analysis.application.core.domain.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class Registration {

    private String description;

    private BigDecimal amount;

    private TypeOperation typeOperation;

    private LocalDate dateOperation;

    private CostCenter costCenter;

    private String supplier;
}

