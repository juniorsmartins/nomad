package com.nomad.accounting.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum CategoryEnum {

    CDB("CDB"),
    POUPANCA("POUPANCA"),
    BOLSA("BOLSA"),
    DOLAR("DOLAR"),
    FUNDO("FUNDO");

    private final String value;

    CategoryEnum(String value) {
        this.value = value;
    }
}

