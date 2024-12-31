package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.Cashbook;

public interface CashbookCreateInputPort {

    Cashbook create(Cashbook cashBook);
}

