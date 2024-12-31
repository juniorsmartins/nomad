package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.entity.RegistrationVo;
import com.nomad.accounting.application.core.domain.Registration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapperOut {

    RegistrationVo toRegistrationVo(Registration registration);
}

