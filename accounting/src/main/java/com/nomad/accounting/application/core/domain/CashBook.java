package com.nomad.accounting.application.core.domain;

import lombok.*;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"cashBookId"})
public class CashBook {

    private UUID cashBookId;

    private Year yearReference;

    private String document;

    private List<Registration> registrations;
}

