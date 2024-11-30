package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.input.CashBookFindByIdInputPort;
import com.nomad.accounting.application.port.output.CashBookFindByIdOutputPort;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashBookFindByIdService implements CashBookFindByIdInputPort {

    private final CashBookFindByIdOutputPort cashBookFindByIdOutputPort;

    @Override
    public CashBook findById(@NotNull final UUID cashBookId) {

        log.info("Serviço iniciado: {}", cashBookId);

        var cashBookFinded = Optional.of(cashBookId)
                    .map(cashBookFindByIdOutputPort::findById)
                    .orElseThrow();

        log.info("Serviço concluído: {}", cashBookFinded);

        return cashBookFinded;
    }
}

