package com.nomad.accounting.application.port.input;

import java.util.UUID;

public interface InvestmentDeleteInputPort {

    void delete(UUID investmentId);
}

