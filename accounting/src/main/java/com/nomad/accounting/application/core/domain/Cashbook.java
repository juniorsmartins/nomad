package com.nomad.accounting.application.core.domain;

import lombok.*;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"cashbookId"})
public class Cashbook {

    private UUID cashbookId;

    private Year yearReference;

    private String document;

    private List<Registration> registrations;

    private List<Investment> investments;
}

