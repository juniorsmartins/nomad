package com.nomad.accounting.application.port.output;

import java.util.UUID;

public interface RegistrationDeleteOutputPort {

    void delete(UUID registrationId);
}

