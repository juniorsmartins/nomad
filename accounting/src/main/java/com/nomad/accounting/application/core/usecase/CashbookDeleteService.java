package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.port.input.CashbookDeleteInputPort;
import com.nomad.accounting.application.port.output.CashbookDeleteOutputPort;
import com.nomad.accounting.application.port.output.CashbookFindByIdOutputPort;
import com.nomad.accounting.config.exception.http409.CashBookConflictRulesException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashbookDeleteService implements CashbookDeleteInputPort {

    private final CashbookFindByIdOutputPort cashBookFindByIdOutputPort;

    private final CashbookDeleteOutputPort cashBookDeleteOutputPort;

    @Override
    public void delete(@NonNull final UUID cashBookId) {

        log.info("Serviço Delete iniciado: {}", cashBookId);

        validateDeletionRules(cashBookFindByIdOutputPort.findById(cashBookId));
        cashBookDeleteOutputPort.delete(cashBookId);

        log.info("Serviço Delete concluído: {}", cashBookId);
    }

    private void validateDeletionRules(Cashbook cashBook) {
        if (!cashBook.getRegistrations().isEmpty()) {
            throw new CashBookConflictRulesException(cashBook.getCashbookId());
        }
    }
}

