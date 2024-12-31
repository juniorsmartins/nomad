package com.nomad.accounting.application.port.output;

import com.nomad.accounting.adapter.entity.CashBookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CashBookFindAllOutputPort {

    Page<CashBookEntity> findAll(Pageable pagination);
}

