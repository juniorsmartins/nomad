package com.nomad.accounting.config.exception.http409;

import java.io.Serial;
import java.util.UUID;

public final class CashbookConflictRulesException extends ResourceConflictRulesException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CashbookConflictRulesException(final UUID cashBookId) {
        super("exception.resource.conflict.rules.cashbook", cashBookId);
    }
}

