package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.Registration;

import java.util.UUID;

public interface RegistrationCreateOutputPort {

    Registration create(UUID cashBookId, Registration registration);
}

