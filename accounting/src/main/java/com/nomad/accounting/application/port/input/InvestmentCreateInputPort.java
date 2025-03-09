package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.Investment;

import java.util.UUID;

public interface InvestmentCreateInputPort {

    Investment create(UUID cashBookId, Investment investment);
}

