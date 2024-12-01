package com.nomad.accounting.adapter.entity;

import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_DESCRIPTION;
import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_SUPPLIER;

@Builder
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"description", "amount", "typeOperation", "dateOperation", "costCenter", "supplier"})
@Embeddable
public final class RegistrationVo {

    @Lob
    @Column(name = "description", length = MAX_CARACTER_DESCRIPTION, nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "type_operation", nullable = false)
    private TypeOperation typeOperation;

    @Column(name = "date_operation", nullable = false)
    private LocalDate dateOperation;

    @Column(name = "cost_center", nullable = false)
    private CostCenter costCenter;

    @Column(name = "supplier", length = MAX_CARACTER_SUPPLIER, nullable = false)
    private String supplier;
}

