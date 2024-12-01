package com.nomad.accounting.application.port.output;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.core.domain.Registration;

import java.util.UUID;

public interface RegistrationCreateOutputPort {

    CashBook create(UUID cashBookId, Registration registration);
}

