package com.nomad.accounting.config.exception.http404;

import lombok.Getter;

import java.io.Serial;
import java.util.UUID;

@Getter
public abstract sealed class ResourceNotFoundException extends RuntimeException permits CashbookNotFoundException,
    RegistrationNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    private final UUID id;

    protected ResourceNotFoundException(final String messageKey, final UUID id) {
        super(messageKey);
        this.messageKey = messageKey;
        this.id = id;
    }
}

