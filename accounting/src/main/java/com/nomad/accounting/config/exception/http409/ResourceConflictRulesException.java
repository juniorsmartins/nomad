package com.nomad.accounting.config.exception.http409;

import lombok.Getter;

import java.io.Serial;
import java.util.UUID;

@Getter
public abstract sealed class ResourceConflictRulesException extends RuntimeException permits CashBookConflictRulesException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    private final UUID id;

    protected ResourceConflictRulesException(final String messageKey, final UUID id) {
        super(messageKey);
        this.messageKey = messageKey;
        this.id = id;
    }
}

