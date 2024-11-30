package com.nomad.accounting.config.exception.http404;

import java.io.Serial;
import java.util.UUID;

public final class RegistrationNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RegistrationNotFoundException(final UUID registrationId) {
        super("exception.resource.not.found.registration", registrationId);
    }
}

