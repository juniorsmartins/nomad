package com.nomad.accounting.config.exception.http404;

import java.io.Serial;
import java.util.UUID;

public final class InvestmentNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvestmentNotFoundException(final UUID investmentId) {
        super("exception.resource.not.found.investment", investmentId);
    }
}

