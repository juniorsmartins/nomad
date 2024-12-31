package com.nomad.accounting.application.port.input;

import java.util.UUID;

public interface CashbookDeleteInputPort {

    void delete(UUID cashBookId);
}

