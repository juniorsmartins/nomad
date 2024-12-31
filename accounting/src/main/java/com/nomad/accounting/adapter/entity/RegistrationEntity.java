package com.nomad.accounting.adapter.entity;

import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_DESCRIPTION;
import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_SUPPLIER;

@Entity
@Table(name = "registrations")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"registrationId"})
public final class RegistrationEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "registration_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID registrationId;

    @ManyToOne
    @JoinColumn(name = "cashbook_id")
    private CashbookEntity cashbook;

    @Lob
    @Column(name = "description", length = MAX_CARACTER_DESCRIPTION, nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "type_operation", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    @Column(name = "date_operation", nullable = false)
    private LocalDate dateOperation;

    @Column(name = "cost_center", nullable = false)
    @Enumerated(EnumType.STRING)
    private CostCenter costCenter;

    @Column(name = "supplier", length = MAX_CARACTER_SUPPLIER, nullable = false)
    private String supplier;
}

