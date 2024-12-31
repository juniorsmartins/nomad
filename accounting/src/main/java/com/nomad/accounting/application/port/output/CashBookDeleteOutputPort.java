package com.nomad.accounting.application.port.output;

import java.util.UUID;

public interface CashBookDeleteOutputPort {

    void delete(UUID cashBookId);
}

