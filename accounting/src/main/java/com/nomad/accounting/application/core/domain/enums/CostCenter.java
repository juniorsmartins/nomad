package com.nomad.accounting.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum CostCenter {

    BANKING("BANKING"),
    BARBERSHOP("BARBERSHOP"),
    CLOTHING("CLOTHING"),
    EDUCATION("EDUCATION"),
    ELETRONIC("ELETRONIC"),
    ENTERTAINMENT("ENTERTAINMENT"),
    FGTS("FGTS"),
    FOOD("FOOD"),
    HEALTH("HEALTH"),
    HOUSE("HOUSE"),
    IRPF("IRPF"),
    OTHER("OTHER"),
    PENSION("PENSION"),
    PET("PET"),
    RENT("RENT"),
    SERVICES("SERVICES"),
    SPORT("SPORT"),
    SUPPLEMENTATION("SUPPLEMENTATION"),
    TELEPHONY("TELEPHONY"),
    TRANSPORT("TRANSPORT"),
    WAGE("WAGE"),
    CDB("CDB"),
    POUPANCA("POUPANCA"),
    BOLSA("BOLSA"),
    DOLAR("DOLAR");

    private final String value;

    CostCenter(String value) {
        this.value = value;
    }
}

