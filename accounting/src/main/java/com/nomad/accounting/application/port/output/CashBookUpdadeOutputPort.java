package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.CashBook;

public interface CashBookUpdadeOutputPort {

    CashBook update(CashBook cashBook);
}

