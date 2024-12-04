package com.nomad.accounting_analysis.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum CostCenter {

    BANKING("BANKING"),
    BARBERSHOP("BARBERSHOP"),
    CLOTHING("CLOTHING"),
    EDUCATION("EDUCATION"),
    ELETRONIC("ELETRONIC"),
    ENTERTAINMENT("ENTERTAINMENT"),
    FOOD("FOOD"),
    HEALTH("HEALTH"),
    HOUSE("HOUSE"),
    OTHER("OTHER"),
    PENSION("PENSION"),
    PET("PET"),
    RENT("RENT"),
    SERVICES("SERVICES"),
    SPORT("SPORT"),
    SUPPLEMENTATION("SUPPLEMENTATION"),
    TELEPHONY("TELEPHONY"),
    TRANSPORT("TRANSPORT"),
    WAGE("WAGE");

    private final String value;

    CostCenter(String value) {
        this.value = value;
    }
}

