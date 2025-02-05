package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.application.port.output.CashbookDeleteOutputPort;
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
public class CashbookDeleteAdapter implements CashbookDeleteOutputPort {

    private final CashbookRepository cashbookRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Override
    public void delete(@NonNull final UUID cashBookId) {

        log.info("Adapter Delete iniciado: {}", cashBookId);

        cashbookRepository.deleteById(cashBookId);

        log.info("Adapter Delete concluído: {}", cashBookId);
    }
}

