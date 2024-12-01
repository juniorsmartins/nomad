package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.core.domain.Registration;
import com.nomad.accounting.application.port.input.RegistrationDeleteInputPort;
import com.nomad.accounting.application.port.output.CashBookCreateOutputPort;
import com.nomad.accounting.application.port.output.CashBookFindByIdOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationDeleteService implements RegistrationDeleteInputPort {

    private final CashBookFindByIdOutputPort cashBookFindByIdOutputPort;

    private final CashBookCreateOutputPort cashBookCreateOutputPort;

    @Override
    public CashBook delete(@NonNull final UUID cashBookId, @NonNull Registration registration) {

        log.info("Serviço Delete iniciado com id: {} {}", cashBookId, registration);

        var cashBook = cashBookFindByIdOutputPort.findById(cashBookId);
        cashBook.getRegistrations()
                .removeIf(registered -> registered.equals(registration));
        cashBookCreateOutputPort.create(cashBook);

        log.info("Serviço Delete concluído: {}", cashBook);

        return cashBook;
    }
}

