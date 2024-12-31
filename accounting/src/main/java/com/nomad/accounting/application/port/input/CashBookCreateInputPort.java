package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.CashBook;

public interface CashBookCreateInputPort {

    CashBook create(CashBook cashBook);
}

