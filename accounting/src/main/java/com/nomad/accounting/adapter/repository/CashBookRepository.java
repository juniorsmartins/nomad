package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.CashbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CashBookRepository extends JpaRepository<CashbookEntity, UUID>
{ }

