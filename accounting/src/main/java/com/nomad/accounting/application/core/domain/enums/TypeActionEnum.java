package com.nomad.accounting.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum TypeActionEnum {

    INVESTMENT("INVESTMENT"),
    DISINVESTMENT("DISINVESTMENT");

    private final String value;

    TypeActionEnum(String value) {
        this.value = value;
    }
}

