package com.nomad.accounting_analysis.core.domain;

import com.nomad.accounting_analysis.core.domain.enums.CostCenterEnum;
import com.nomad.accounting_analysis.core.domain.enums.TypeOperationEnum;
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
        return calculateAnnualSum(TypeOperationEnum.INPUT);
    }

    public BigDecimal annualSumDebits() {
        var annualSumDebits = calculateAnnualSum(TypeOperationEnum.OUTPUT);
        var annualChargeback = calculateAnnualSum(TypeOperationEnum.CHARGEBACK);

        return annualSumDebits.subtract(annualChargeback);
    }

    public BigDecimal annualBalance() {
        var credits = this.annualSumCredits();
        var debits = this.annualSumDebits();

        return credits.subtract(debits);
    }

    private BigDecimal calculateAnnualSum(@NonNull TypeOperationEnum typeOperation) {
        return this.registrations.stream()
                .filter(registration -> typeOperation.equals(registration.getTypeOperationEnum()))
                .map(Registration::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public Map<CostCenterEnum, BigDecimal> annualSumCreditsByCostCenter() {
        var result = calculateAnnualSumByCostCenter(TypeOperationEnum.INPUT);

        return filterZeroValues(result);
    }

    public Map<CostCenterEnum, BigDecimal> annualSumDebitsByCostCenter() {
        var annualSumOutputByCostCenter = calculateAnnualSumByCostCenter(TypeOperationEnum.OUTPUT);
        var annualSumChargebackByCostCenter = calculateAnnualSumByCostCenter(TypeOperationEnum.CHARGEBACK);

        var result = subtractCostCenterMaps(annualSumOutputByCostCenter, annualSumChargebackByCostCenter);

        return filterZeroValues(result);
    }

    private Map<CostCenterEnum, BigDecimal> calculateAnnualSumByCostCenter(@NonNull final TypeOperationEnum typeOperation) {

        Map<CostCenterEnum, BigDecimal> annualSumByCostCenter = new EnumMap<>(CostCenterEnum.class);

        for (CostCenterEnum costCenter : CostCenterEnum.values()) {
            var sum = this.registrations.parallelStream()
                    .filter(registration -> typeOperation.equals(registration.getTypeOperationEnum()))
                    .filter(registration -> costCenter.equals(registration.getCostCenterEnum()))
                    .map(Registration::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            annualSumByCostCenter.put(costCenter, sum);
        }
        return annualSumByCostCenter;
    }

    private Map<CostCenterEnum, BigDecimal> subtractCostCenterMaps(Map<CostCenterEnum, BigDecimal> positiveMap,
                                                               Map<CostCenterEnum, BigDecimal> negativeMap) {

        Map<CostCenterEnum, BigDecimal> result = new EnumMap<>(CostCenterEnum.class);

        for (CostCenterEnum costCenter : CostCenterEnum.values()) {
            var value1 = positiveMap.getOrDefault(costCenter, BigDecimal.ZERO);
            var value2 = negativeMap.getOrDefault(costCenter, BigDecimal.ZERO);

            result.put(costCenter, value1.subtract(value2));
        }
        return result;
    }


    public Map<Month, BigDecimal> monthlySumCredits() {
        return calculateMonthlySum(TypeOperationEnum.INPUT);
    }

    public Map<Month, BigDecimal> monthlySumDebits() {
        var monthlySumDebits = calculateMonthlySum(TypeOperationEnum.OUTPUT);
        var monthlyChargeback = calculateMonthlySum(TypeOperationEnum.CHARGEBACK);

        return subtractMonthlyMaps(monthlySumDebits, monthlyChargeback);
    }

    private Map<Month, BigDecimal> calculateMonthlySum(@NonNull final TypeOperationEnum typeOperation) {
        Map<Month, BigDecimal> monthlySum = new EnumMap<>(Month.class);

        for (Month month : Month.values()) {
            var sum = this.registrations.parallelStream()
                    .filter(registration -> month.equals(registration.getDateOperation().getMonth()))
                    .filter(registration -> typeOperation.equals(registration.getTypeOperationEnum()))
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

