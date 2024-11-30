package com.nomad.accounting.config.exception.http404;

import java.io.Serial;
import java.util.UUID;

public final class CashBookNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CashBookNotFoundException(final UUID cashBookId) {
        super("exception.resource.not.found.cashbook", cashBookId);
    }
}

