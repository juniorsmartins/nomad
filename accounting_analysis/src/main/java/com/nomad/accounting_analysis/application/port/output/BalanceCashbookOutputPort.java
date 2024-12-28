package com.nomad.accounting_analysis.application.port.output;

import com.nomad.accounting_analysis.adapter.dto.in.CashbookDtoResponse;

import java.util.UUID;

public interface BalanceCashbookOutputPort {

    CashbookDtoResponse findById(UUID id);
}

