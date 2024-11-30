package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.port.input.CashBookDeleteInputPort;
import com.nomad.accounting.application.port.output.CashBookDeleteOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashBookDeleteService implements CashBookDeleteInputPort {

    private final CashBookDeleteOutputPort cashBookDeleteOutputPort;

    @Override
    public void delete(final UUID cashBookId) {

        log.info("Serviço iniciado: {}", cashBookId);

        Optional.ofNullable(cashBookId)
                .ifPresentOrElse(cashBookDeleteOutputPort::delete,
                        () -> {throw new NoSuchElementException();}
                );

        log.info("Serviço concluído: {}", cashBookId);
    }
}

