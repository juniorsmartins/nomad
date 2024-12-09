package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.CashBook;
import com.nomad.accounting.application.port.input.CashBookDeleteInputPort;
import com.nomad.accounting.application.port.output.CashBookDeleteOutputPort;
import com.nomad.accounting.application.port.output.CashBookFindByIdOutputPort;
import com.nomad.accounting.config.exception.http409.CashBookConflictRulesException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashBookDeleteService implements CashBookDeleteInputPort {

    private final CashBookFindByIdOutputPort cashBookFindByIdOutputPort;

    private final CashBookDeleteOutputPort cashBookDeleteOutputPort;

    @Override
    public void delete(@NonNull final UUID cashBookId) {

        log.info("Serviço Delete iniciado: {}", cashBookId);

        validateDeletionRules(cashBookFindByIdOutputPort.findById(cashBookId));
        cashBookDeleteOutputPort.delete(cashBookId);

        log.info("Serviço Delete concluído: {}", cashBookId);
    }

    private void validateDeletionRules(CashBook cashBook) {
        if (!cashBook.getRegistrations().isEmpty()) {
            throw new CashBookConflictRulesException(cashBook.getCashbookId());
        }
    }
}

