package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.CashBook;

public interface CashBookUpdateInputPort {

    CashBook update(CashBook cashBook);
}

