package com.nomad.accounting_analysis.port.output;

import com.nomad.accounting_analysis.port.dto.CashbookDtoResponse;

import java.util.UUID;

public interface BalanceCashbookOutputPort {

    CashbookDtoResponse findById(UUID id);
}

