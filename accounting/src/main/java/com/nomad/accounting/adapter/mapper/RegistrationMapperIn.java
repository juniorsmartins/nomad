package com.nomad.accounting.adapter.mapper;

import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.RegistrationDtoResponse;
import com.nomad.accounting.adapter.dto.out.RegistrationFindDtoResponse;
import com.nomad.accounting.adapter.dto.out.RegistrationSearchDtoResponse;
import com.nomad.accounting.adapter.entity.RegistrationEntity;
import com.nomad.accounting.application.core.domain.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationMapperIn {

}

