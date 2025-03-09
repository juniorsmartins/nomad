package com.nomad.accounting.application.core.usecase;

import com.nomad.accounting.application.core.domain.Investment;
import com.nomad.accounting.application.port.input.InvestmentCreateInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvestmentCreateService implements InvestmentCreateInputPort {

    @Override
    public Investment create(UUID cashBookId, Investment investment) {
        return investment;
    }
}

