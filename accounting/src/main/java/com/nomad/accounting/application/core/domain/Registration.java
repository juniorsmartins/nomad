package com.nomad.accounting.application.core.domain;

import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"description", "amount", "typeOperation", "dateOperation", "costCenter", "supplier"})
public class Registration {

    private String description;

    private BigDecimal amount;

    private TypeOperation typeOperation;

    private LocalDate dateOperation;

    private CostCenter costCenter;

    private String supplier;
}

