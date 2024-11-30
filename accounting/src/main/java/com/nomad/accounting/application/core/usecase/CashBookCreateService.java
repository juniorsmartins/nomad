package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.input.CashBookCreateInputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CashBookCreateService implements CashBookCreateInputPort {

    @Override
    public CashBook create(@NonNull CashBook cashBook) {
        return cashBook;
    }
}

