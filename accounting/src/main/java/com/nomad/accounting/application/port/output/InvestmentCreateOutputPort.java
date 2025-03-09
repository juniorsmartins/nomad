package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.Investment;

import java.util.UUID;

public interface InvestmentCreateOutputPort {

    Investment create(UUID cashbookId, Investment investment);
}

