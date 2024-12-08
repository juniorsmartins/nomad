package com.nomad.accounting.application.port.output;

import com.nomad.accounting.adapter.dto.filter.CashBookFilter;
import com.nomad.accounting.adapter.entity.CashBookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CashBookSearchOutputPort {

    Page<CashBookEntity> search(final CashBookFilter cashBookFilter, Pageable pagination);
}

