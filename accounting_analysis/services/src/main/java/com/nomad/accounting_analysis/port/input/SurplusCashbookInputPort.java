package com.nomad.accounting_analysis.port.input;

import com.nomad.accounting_analysis.core.domain.BalanceCashbook;

import java.util.UUID;

public interface SurplusCashbookInputPort {

    BalanceCashbook surplus(UUID id);
}

