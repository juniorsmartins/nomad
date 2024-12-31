package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, UUID>, JpaSpecificationExecutor<RegistrationEntity>
{ }

