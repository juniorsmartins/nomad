package com.nomad.accounting.application.port.input;

import java.util.UUID;

public interface CashBookDeleteInputPort {

    void delete(UUID cashBookId);
}

