package com.nomad.accounting.adapter.repository;

import com.nomad.accounting.adapter.entity.InvestmentEntity;
import com.nomad.accounting.application.port.output.InvestmentFindByIdOutputPort;
import com.nomad.accounting.config.exception.http404.InvestmentNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InvestmentFindByIdAdapter implements InvestmentFindByIdOutputPort {

    private final InvestmentRepository investmentRepository;

    @Transactional(readOnly = true)
    @Override
    public InvestmentEntity findById(@NonNull final UUID investmentId) {

        log.info("Adaptador findById iniciado: {}", investmentId);

        var investmentFind = investmentRepository.findById(investmentId)
                    .orElseThrow(() -> new InvestmentNotFoundException(investmentId));

        log.info("Adaptador findById concluído: {}", investmentId);

        return investmentFind;
    }
}

