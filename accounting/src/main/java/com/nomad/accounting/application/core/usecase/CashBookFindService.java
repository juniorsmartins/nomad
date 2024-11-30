package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.input.CashBookFindInputPort;
import com.nomad.accounting.application.port.output.CashBookFindOutputPort;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashBookFindService implements CashBookFindInputPort {

    private final CashBookFindOutputPort cashBookFindOutputPort;

    @Override
    public CashBook findById(@NotNull final UUID id) {

        log.info("Serviço iniciado: {}", id);

        var cashBookFinded = Optional.of(id)
                    .map(cashBookFindOutputPort::findById)
                    .orElseThrow();

        log.info("Serviço concluído: {}", cashBookFinded);

        return cashBookFinded;
    }
}

