package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.Cashbook;

public interface CashbookUpdateInputPort {

    Cashbook update(Cashbook cashBook);
}

