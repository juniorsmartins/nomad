package com.nomad.accounting.application.port.output;

import com.nomad.accounting.adapter.dto.filter.CashbookFilter;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CashbookSearchOutputPort {

    Page<CashbookEntity> search(final CashbookFilter cashBookFilter, Pageable pagination);
}

