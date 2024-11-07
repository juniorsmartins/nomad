package com.nomad.accounting.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum TypeOperation {

    DEBIT("DEBIT"),
    CREDIT("CREDIT");

    private final String value;

    TypeOperation(String value) {
        this.value = value;
    }
}

