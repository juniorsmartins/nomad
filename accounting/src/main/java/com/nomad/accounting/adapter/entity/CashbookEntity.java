package com.nomad.accounting.adapter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.UUID;

import static com.nomad.accounting.config.AccountingConstants.MAX_CARACTER_DOCUMENT;

@Entity
@Table(name = "cashbooks",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_cashbooks_year_document", columnNames = {"year_reference", "document"})
    }
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"cashbookId"})
public final class CashbookEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cashbook_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID cashbookId;

    @Column(name = "year_reference", nullable = false)
    private Year yearReference;

    @Column(name = "document", length = MAX_CARACTER_DOCUMENT, nullable = false)
    private String document;

    @OneToMany(mappedBy = "cashbook", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RegistrationEntity> registrations;
}

