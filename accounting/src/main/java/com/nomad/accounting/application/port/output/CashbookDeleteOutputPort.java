package com.nomad.accounting.application.port.output;

import java.util.UUID;

public interface CashbookDeleteOutputPort {

    void delete(UUID cashBookId);
}

