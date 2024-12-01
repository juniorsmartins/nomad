package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.RegistrationUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.RegistrationDtoResponse;
import com.nomad.accounting.adapter.entity.RegistrationVo;
import com.nomad.accounting.application.core.domain.Registration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapperIn {

    Registration toRegistration(RegistrationCreateDtoRequest registrationCreateDtoRequest);

    Registration toRegistration(RegistrationUpdateDtoRequest registrationUpdateDtoRequest);

    RegistrationDtoResponse toRegistrationDtoResponse(RegistrationVo registrationVo);
}

