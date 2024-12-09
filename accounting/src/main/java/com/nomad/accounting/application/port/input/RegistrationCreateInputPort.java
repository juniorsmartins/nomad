package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.Registration;

import java.util.UUID;

public interface RegistrationCreateInputPort {

    Registration create(UUID cashBookId, Registration registration);
}

