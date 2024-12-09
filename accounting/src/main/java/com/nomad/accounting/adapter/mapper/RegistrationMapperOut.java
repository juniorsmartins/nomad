package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.entity.RegistrationEntity;
import com.nomad.accounting.application.core.domain.Registration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapperOut {

    RegistrationEntity toRegistrationEntity(Registration registration);

    Registration toRegistration(RegistrationEntity registrationEntity);
}

