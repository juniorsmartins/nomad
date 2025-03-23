package com.nomad.accounting_analysis.port.input;

import com.nomad.accounting_analysis.core.domain.BalanceCashbook;

import java.util.UUID;

public interface DebitsCashbookInputPort {

    BalanceCashbook debits(UUID id);
}

