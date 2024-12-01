package com.nomad.accounting.application.port.input;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.core.domain.Registration;

import java.util.UUID;

public interface RegistrationDeleteInputPort {

    CashBook delete(UUID cashBookId, Registration registration);
}

