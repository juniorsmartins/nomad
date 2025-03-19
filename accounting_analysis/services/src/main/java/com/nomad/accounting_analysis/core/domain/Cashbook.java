package com.nomad.accounting_analysis.core.domain;

import com.nomad.accounting_analysis.application.core.domain.enums.CostCenter;
import com.nomad.accounting_analysis.application.core.domain.enums.TypeOperation;
import lombok.*;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class Cashbook {

    private UUID cashbookId;

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

    public BigDecimal annualBalance() {
        var credits = this.annualSumCredits();
        var debits = this.annualSumDebits();

        return credits.subtract(debits);
    }

    public BigDecimal annualSumInvestment() {
        var annualSumInvestment = calculateAnnualSum(TypeOperation.INVESTMENT);
        var annualSumDisinvestment = calculateAnnualSum(TypeOperation.DISINVESTMENT);

        return annualSumInvestment.subtract(annualSumDisinvestment);
    }

    private BigDecimal calculateAnnualSum(@NonNull TypeOperation typeOperation) {
        return this.registrations.stream()
                .filter(registration -> typeOperation.equals(registration.getTypeOperation()))
                .map(Registration::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public Map<CostCenter, BigDecimal> annualSumCreditsByCostCenter() {
        var result = calculateAnnualSumByCostCenter(TypeOperation.INPUT);

        return filterZeroValues(result);
    }

    public Map<CostCenter, BigDecimal> annualSumDebitsByCostCenter() {
        var annualSumOutputByCostCenter = calculateAnnualSumByCostCenter(TypeOperation.OUTPUT);
        var annualSumChargebackByCostCenter = calculateAnnualSumByCostCenter(TypeOperation.CHARGEBACK);

        var result = subtractCostCenterMaps(annualSumOutputByCostCenter, annualSumChargebackByCostCenter);

        return filterZeroValues(result);
    }

    public Map<CostCenter, BigDecimal> annualSumInvestmentByCostCenter() {
        var annualInvestmentByCostCenter = calculateAnnualSumByCostCenter(TypeOperation.INVESTMENT);
        var annualDisinvestmentByCostCenter = calculateAnnualSumByCostCenter(TypeOperation.DISINVESTMENT);

        var result = subtractCostCenterMaps(annualInvestmentByCostCenter, annualDisinvestmentByCostCenter);

        return filterZeroValues(result);
    }

    private Map<CostCenter, BigDecimal> calculateAnnualSumByCostCenter(@NonNull final TypeOperation typeOperation) {

        Map<CostCenter, BigDecimal> annualSumByCostCenter = new EnumMap<>(CostCenter.class);

        for (CostCenter costCenter : CostCenter.values()) {
            var sum = this.registrations.parallelStream()
                    .filter(registration -> typeOperation.equals(registration.getTypeOperation()))
                    .filter(registration -> costCenter.equals(registration.getCostCenter()))
                    .map(Registration::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            annualSumByCostCenter.put(costCenter, sum);
        }
        return annualSumByCostCenter;
    }

    private Map<CostCenter, BigDecimal> subtractCostCenterMaps(Map<CostCenter, BigDecimal> positiveMap, Map<CostCenter, BigDecimal> negativeMap) {
        Map<CostCenter, BigDecimal> result = new EnumMap<>(CostCenter.class);

        for (CostCenter costCenter : CostCenter.values()) {
            var value1 = positiveMap.getOrDefault(costCenter, BigDecimal.ZERO);
            var value2 = negativeMap.getOrDefault(costCenter, BigDecimal.ZERO);

            result.put(costCenter, value1.subtract(value2));
        }
        return result;
    }


    public Map<Month, BigDecimal> monthlySumCredits() {
        return calculateMonthlySum(TypeOperation.INPUT);
    }

    public Map<Month, BigDecimal> monthlySumDebits() {
        var monthlySumDebits = calculateMonthlySum(TypeOperation.OUTPUT);
        var monthlyChargeback = calculateMonthlySum(TypeOperation.CHARGEBACK);

        return subtractMonthlyMaps(monthlySumDebits, monthlyChargeback);
    }

    private Map<Month, BigDecimal> calculateMonthlySum(@NonNull final TypeOperation typeOperation) {
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

        return subtractMonthlyMaps(monthlySumCredits, monthlySumDebits);
    }

    public Map<Month, BigDecimal> monthlyInvestment() {
        var monthlyInvestment = calculateMonthlySum(TypeOperation.INVESTMENT);
        var monthlyDisinvestment = calculateMonthlySum(TypeOperation.DISINVESTMENT);

        return subtractMonthlyMaps(monthlyInvestment, monthlyDisinvestment);
    }

    private Map<Month, BigDecimal> subtractMonthlyMaps(Map<Month, BigDecimal> positiveMap, Map<Month, BigDecimal> negativeMap) {
        Map<Month, BigDecimal> result = new EnumMap<>(Month.class);

        for (Month month : Month.values()) {
            var value1 = positiveMap.getOrDefault(month, BigDecimal.ZERO);
            var value2 = negativeMap.getOrDefault(month, BigDecimal.ZERO);

            result.put(month, value1.subtract(value2));
        }
        return result;
    }

    private <K> Map<K, BigDecimal> filterZeroValues(Map<K, BigDecimal> inputMap) {
        return inputMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().compareTo(BigDecimal.ZERO) != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

