package com.nomad.accounting_analysis.application.port.input;

import com.nomad.accounting_analysis.application.core.domain.BalanceCashbook;

import java.util.UUID;

public interface SurplusCashbookInputPort {

    BalanceCashbook surplus(UUID id);
}

