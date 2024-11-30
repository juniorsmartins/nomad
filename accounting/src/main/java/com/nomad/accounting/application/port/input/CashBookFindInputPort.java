package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.CashBook;

import java.util.UUID;

public interface CashBookFindInputPort {

    CashBook findById(UUID id);
}

