package com.nomad.accounting.application.port.output;

import java.util.UUID;

public interface InvestmentDeleteOutputPort {

    void delete(UUID investmentId);
}

