package com.nomad.accounting.adapter.entity;

import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;
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
@Table(name = "investments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"investmentId"})
public final class InvestmentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "investment_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID investmentId;

    @ManyToOne
    @JoinColumn(name = "cashbook_id")
    private CashbookEntity cashbook;

    @Column(name = "description", length = MAX_CARACTER_DESCRIPTION, nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "type_action", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeActionEnum typeActionEnum;

    @Column(name = "date_operation", nullable = false)
    private LocalDate dateOperation;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryEnum;

    @Column(name = "supplier", length = MAX_CARACTER_SUPPLIER, nullable = false)
    private String supplier;
}

