package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.CashBook;

import java.util.UUID;

public interface CashBookFindOutputPort {

    CashBook findById(UUID cashBookId);
}

