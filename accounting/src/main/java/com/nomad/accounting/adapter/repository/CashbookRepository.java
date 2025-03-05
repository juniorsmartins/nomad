package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.CashbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CashbookRepository extends JpaRepository<CashbookEntity, UUID>, JpaSpecificationExecutor<CashbookEntity>
{
    Optional<CashbookEntity> findByYearReferenceAndDocument(Year yearReference, String document);
}

