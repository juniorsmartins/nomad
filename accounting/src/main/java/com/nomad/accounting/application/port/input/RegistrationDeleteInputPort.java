package com.nomad.accounting.application.port.input;

import java.util.UUID;

public interface RegistrationDeleteInputPort {

    void delete(UUID registrationId);
}

