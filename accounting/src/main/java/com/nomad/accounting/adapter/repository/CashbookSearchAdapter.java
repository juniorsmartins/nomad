package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.dto.filter.CashbookFilter;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.adapter.repository.specs.CashbookFactorySpec;
import com.nomad.accounting.application.port.output.CashbookSearchOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashbookSearchAdapter implements CashbookSearchOutputPort {

    private final CashbookRepository cashBookRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<CashbookEntity> search(final CashbookFilter cashBookFilter, final Pageable pagination) {

        log.info("Adapter Search iniciado: {}", cashBookFilter);

        var cashbookSearch = Optional.ofNullable(cashBookFilter)
            .map(filters -> this.cashBookRepository
                    .findAll(CashbookFactorySpec.dynamicQuery(filters), pagination))
            .orElseThrow();

        log.info("Adapter Search concluído: {}", cashbookSearch);

        return cashbookSearch;
    }
}

