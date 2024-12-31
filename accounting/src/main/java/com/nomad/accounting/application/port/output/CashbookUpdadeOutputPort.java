package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.Cashbook;

public interface CashbookUpdadeOutputPort {

    Cashbook update(Cashbook cashBook);
}

