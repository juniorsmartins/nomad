package com.nomad.accounting_analysis.core.domain.enums;

import lombok.Getter;

@Getter
public enum CostCenterEnum {

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
    WAGE("WAGE");

    private final String value;

    CostCenterEnum(String value) {
        this.value = value;
    }
}

