package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.application.port.output.CashBookFindAllOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashBookFindAllAdapter implements CashBookFindAllOutputPort {

    private final CashBookRepository cashBookRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<CashBookEntity> findAll(Pageable pagination) {

        log.info("Adapter iniciado com paginação: {}", pagination);

        var cashBookAll = cashBookRepository.findAll(pagination);

        log.info("Adapter concluído: {}", pagination);

        return cashBookAll;
    }
}

