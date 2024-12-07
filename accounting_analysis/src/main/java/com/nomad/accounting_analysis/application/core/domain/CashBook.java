package com.nomad.accounting_analysis.application.core.domain;

import com.nomad.accounting_analysis.application.core.domain.enums.TypeOperation;
import lombok.*;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.*;

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
        return calculateAnnualSum(TypeOperation.INPUT);
    }

    public BigDecimal annualSumDebits() {
        var annualSumDebits = calculateAnnualSum(TypeOperation.OUTPUT);
        var annualChargeback = calculateAnnualSum(TypeOperation.CHARGEBACK);
        return annualSumDebits.subtract(annualChargeback);
    }

    private BigDecimal calculateAnnualSum(@NonNull TypeOperation typeOperation) {
        return this.registrations.stream()
                .filter(registration -> typeOperation.equals(registration.getTypeOperation()))
                .map(Registration::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal annualBalance() {
        var credits = this.annualSumCredits();
        var debits = this.annualSumDebits();
        return credits.subtract(debits);
    }

    public Map<Month, BigDecimal> monthlySumCredits() {
        return calculateMonthlySum(TypeOperation.INPUT);
    }

    public Map<Month, BigDecimal> monthlySumDebits() {
        var monthlySumDebits = calculateMonthlySum(TypeOperation.OUTPUT);
        var monthlyChargeback = calculateMonthlySum(TypeOperation.CHARGEBACK);

        Map<Month, BigDecimal> debitLessTurn = new EnumMap<>(Month.class);

        for (Month month : Month.values()) {
           debitLessTurn.put(month, monthlySumDebits.getOrDefault(month, BigDecimal.ZERO)
                    .subtract(monthlyChargeback.getOrDefault(month, BigDecimal.ZERO)));
        }
        return debitLessTurn;
    }

    private Map<Month, BigDecimal> calculateMonthlySum(@NonNull TypeOperation typeOperation) {
        Map<Month, BigDecimal> monthlySum = new EnumMap<>(Month.class);

        for (Month month : Month.values()) {
            var sum = this.registrations.parallelStream()
                    .filter(registration -> month.equals(registration.getDateOperation().getMonth()))
                    .filter(registration -> typeOperation.equals(registration.getTypeOperation()))
                    .map(Registration::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            monthlySum.put(month, sum);
        }
        return monthlySum;
    }

    public Map<Month, BigDecimal> monthlyBalance() {
        Map<Month, BigDecimal> monthlySumCredits = monthlySumCredits();
        Map<Month, BigDecimal> monthlySumDebits = monthlySumDebits();

        Map<Month, BigDecimal> monthBalance = new EnumMap<>(Month.class);

        for (Month month : Month.values()) {
            monthBalance.put(month, monthlySumCredits.getOrDefault(month, BigDecimal.ZERO)
                    .subtract(monthlySumDebits.getOrDefault(month, BigDecimal.ZERO)));
        }
        return monthBalance;
    }
}

