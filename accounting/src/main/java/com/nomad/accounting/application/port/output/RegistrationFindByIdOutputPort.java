package com.nomad.accounting.application.port.output;

import com.nomad.accounting.adapter.entity.RegistrationEntity;

import java.util.UUID;

public interface RegistrationFindByIdOutputPort {

    RegistrationEntity findById(UUID registrationId);
}

