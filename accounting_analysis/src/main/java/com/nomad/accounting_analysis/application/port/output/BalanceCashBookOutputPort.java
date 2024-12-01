package com.nomad.accounting_analysis.application.port.output;

import com.nomad.accounting_analysis.adapter.dto.in.CashBookDtoResponse;

import java.util.UUID;

public interface BalanceCashBookOutputPort {

    CashBookDtoResponse findById(UUID id);
}

