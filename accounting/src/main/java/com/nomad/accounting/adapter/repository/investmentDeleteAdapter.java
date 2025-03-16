package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.InvestmentEntity;
import com.nomad.accounting.application.port.output.InvestmentDeleteOutputPort;
import com.nomad.accounting.config.exception.http404.InvestmentNotFoundException;
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
public class investmentDeleteAdapter implements InvestmentDeleteOutputPort {

    private final InvestmentRepository investmentRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Override
    public void delete(@NonNull final UUID investmentId) {

        log.info("Adapter delete iniciado: {}", investmentId);

        var cashbookEntity = investmentRepository.findById(investmentId)
                .map(InvestmentEntity::getCashbook)
                .orElseThrow(() -> new InvestmentNotFoundException(investmentId));

        cashbookEntity.getInvestments()
                .removeIf(investment -> investment.getInvestmentId().equals(investmentId));

        investmentRepository.deleteById(investmentId);

        log.info("Adapter delete concluído: {}", investmentId);
    }
}

