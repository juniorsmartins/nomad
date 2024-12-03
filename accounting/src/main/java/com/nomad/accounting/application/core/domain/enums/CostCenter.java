package com.nomad.accounting.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum CostCenter {

    FOOD("FOOD"),
    RENT("RENT"),
    EDUCATION("EDUCATION"),
    ELETRONIC("ELETRONIC"),
    SPORT("SPORT"),
    HOUSE("HOUSE"),
    ENTERTAINMENT("ENTERTAINMENT"),
    PET("PET"),
    PENSION("PENSION"),
    WAGE("WAGE"),
    HEALTH("HEALTH"),
    SERVICES("SERVICES"),
    SUPPLEMENTATION("SUPPLEMENTATION"),
    TELEPHONY("TELEPHONY"),
    TRANSPORT("TRANSPORT"),
    CLOTHING("CLOTHING"),
    BARBERSHOP("BARBERSHOP"),
    OTHER("OTHER");

    private final String value;

    CostCenter(String value) {
        this.value = value;
    }
}

