package com.nomad.accounting_analysis.application.port.input;

import com.nomad.accounting_analysis.application.core.domain.BalanceCashBook;

import java.util.UUID;

public interface BalanceCashBookInputPort {

    BalanceCashBook annual(UUID id);
}

