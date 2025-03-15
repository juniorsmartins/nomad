package com.nomad.accounting.application.port.output;

import com.nomad.accounting.adapter.entity.InvestmentEntity;

import java.util.UUID;

public interface InvestmentFindByIdOutputPort {

    InvestmentEntity findById(UUID investmentId);
}

