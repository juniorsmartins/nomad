package com.nomad.accounting.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum TypeOperation {

    INPUT("INPUT"),
    OUTPUT("OUTPUT"),
    CHARGEBACK("CHARGEBACK");

    private final String value;

    TypeOperation(String value) {
        this.value = value;
    }
}

