package com.nomad.accounting_analysis.core.domain.enums;

import lombok.Getter;

@Getter
public enum TypeOperation {

    INPUT("INPUT"),
    OUTPUT("OUTPUT"),
    CHARGEBACK("CHARGEBACK"),
    INVESTMENT("INVESTMENT"),
    DISINVESTMENT("DISINVESTMENT");

    private final String value;

    TypeOperation(String value) {
        this.value = value;
    }
}

