package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.application.port.output.CashBookDeleteOutputPort;
import com.nomad.accounting.config.exception.http404.CashBookNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashBookDeleteAdapter implements CashBookDeleteOutputPort {

    private final CashBookRepository cashBookRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Override
    public void delete(@NonNull final UUID cashBookId) {

        log.info("Adapter Delete iniciado: {}", cashBookId);

        cashBookRepository.findById(cashBookId)
                .ifPresentOrElse(cashBookRepository::delete,
                        () -> {throw new CashBookNotFoundException(cashBookId);}
                );

        log.info("Adapter Delete concluído: {}", cashBookId);
    }
}

