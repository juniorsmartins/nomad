package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.Cashbook;

import java.util.UUID;

public interface CashbookFindByIdOutputPort {

    Cashbook findById(UUID cashBookId);
}

