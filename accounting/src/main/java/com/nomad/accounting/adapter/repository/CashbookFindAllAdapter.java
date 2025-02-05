package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.application.port.output.CashbookFindAllOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashbookFindAllAdapter implements CashbookFindAllOutputPort {

    private final CashbookRepository cashbookRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<CashbookEntity> findAll(@NonNull final Pageable pagination) {

        log.info("Adapter FindAll iniciado com paginação: {}", pagination);

        var cashBookAll = cashbookRepository.findAll(pagination);

        log.info("Adapter FindAll concluído: {}", pagination);

        return cashBookAll;
    }
}

