package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.application.port.output.CashBookDeleteOutputPort;
import com.nomad.accounting.config.exception.http404.CashBookNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CashBookDeleteAdapter implements CashBookDeleteOutputPort {

    private final CashBookRepository cashBookRepository;

    @Override
    public void delete(@NonNull final UUID cashBookId) {

        cashBookRepository.findById(cashBookId)
                .ifPresentOrElse(cashBookRepository::delete,
                        () -> {throw new CashBookNotFoundException(cashBookId);}
                );
    }
}

