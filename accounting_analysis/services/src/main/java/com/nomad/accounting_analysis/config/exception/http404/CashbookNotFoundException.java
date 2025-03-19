package com.nomad.accounting_analysis.config.exception.http404;

import java.io.Serial;
import java.util.UUID;

public final class CashbookNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CashbookNotFoundException(final UUID cashBookId) {
        super("exception.resource.not.found.cashbook", cashBookId);
    }
}

