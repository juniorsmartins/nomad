package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.InvestmentEntity;
import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.core.domain.Investment;
import com.nomad.accounting.application.port.output.InvestmentCreateOutputPort;
import com.nomad.accounting.config.exception.http404.CashBookNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InvestmentCreateAdapter implements InvestmentCreateOutputPort {

    private final CashbookRepository cashbookRepository;

    private final InvestmentRepository investmentRepository;

    private final CentralMapper centralMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Investment create(@NonNull final UUID cashbookId, @NonNull Investment investment) {

        log.info("Adaptador create iniciado para cashbookId: {} {}", cashbookId, investment);

        var investmentCreated = Optional.of(investment)
                .map(centralMapper::toInvestmentEntity)
                .map(entity -> investmentRepository.save(verifyCashbook(cashbookId, entity)))
                .map(centralMapper::toInvestment)
                .orElseThrow();

        log.info("Adaptador create concluído: {}", investmentCreated);

        return investmentCreated;
    }

    private InvestmentEntity verifyCashbook(UUID cashbookId, InvestmentEntity investmentEntity) {
        return cashbookRepository.findById(cashbookId)
                .map(cashbookEntity -> {
                    investmentEntity.setCashbook(cashbookEntity);
                    return investmentEntity;
                })
                .orElseThrow(() -> new CashBookNotFoundException(cashbookId));
    }
}

