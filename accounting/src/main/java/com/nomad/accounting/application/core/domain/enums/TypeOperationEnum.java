package com.nomad.accounting.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum TypeOperationEnum {

    INPUT("INPUT"),
    OUTPUT("OUTPUT"),
    CHARGEBACK("CHARGEBACK");

    private final String value;

    TypeOperationEnum(String value) {
        this.value = value;
    }
}

