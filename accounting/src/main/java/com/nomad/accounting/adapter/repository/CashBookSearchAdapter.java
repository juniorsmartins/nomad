package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.dto.filter.CashBookFilter;
import com.nomad.accounting.adapter.entity.CashBookEntity;
import com.nomad.accounting.adapter.repository.specs.CashBookFactorySpec;
import com.nomad.accounting.application.port.output.CashBookSearchOutputPort;
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
public class CashBookSearchAdapter implements CashBookSearchOutputPort {

    private final CashBookRepository cashBookRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<CashBookEntity> search(final CashBookFilter cashBookFilter, final Pageable pagination) {

        log.info("Adapter Search iniciado: {}", cashBookFilter);

        var cashbookSearch = Optional.ofNullable(cashBookFilter)
            .map(filters -> this.cashBookRepository
                    .findAll(CashBookFactorySpec.dynamicQuery(filters), pagination))
            .orElseThrow();

        log.info("Adapter Search concluído: {}", cashbookSearch);

        return cashbookSearch;
    }
}

