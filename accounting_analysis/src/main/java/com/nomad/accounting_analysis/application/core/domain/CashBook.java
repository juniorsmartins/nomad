package com.nomad.accounting_analysis.application.core.domain;

import com.nomad.accounting_analysis.application.core.domain.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class CashBook {

    private UUID cashBookId;

    private Year yearReference;

    private String document;

    private List<Registration> registrations;

    public BigDecimal annualSumCredits() {
        return this.registrations.stream()
                .filter(registration -> TypeOperation.INPUT.equals(registration.getTypeOperation()))
                .map(Registration::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal annualSumDebits() {
        return this.registrations.stream()
                .filter(registration -> TypeOperation.OUTPUT.equals(registration.getTypeOperation()))
                .map(Registration::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal annualBalance() {
        var credits = this.annualSumCredits();
        var debits = this.annualSumDebits();
        return credits.subtract(debits);
    }
}

