package com.nomad.accounting_analysis.port.input;

import com.nomad.accounting_analysis.application.core.domain.BalanceCashbook;

import java.util.UUID;

public interface BalanceCashbookInputPort {

    BalanceCashbook annual(UUID id);
}

