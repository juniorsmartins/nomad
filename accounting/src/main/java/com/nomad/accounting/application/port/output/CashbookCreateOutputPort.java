package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.Cashbook;

public interface CashbookCreateOutputPort {

    Cashbook create(Cashbook cashBook);
}

