package com.nomad.accounting_analysis.adapter.client;

import com.nomad.accounting_analysis.adapter.dto.in.CashBookDtoResponse;
import com.nomad.accounting_analysis.application.port.output.BalanceCashBookOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceCashBookClient implements BalanceCashBookOutputPort {

    private static final String URI_ACCOUNTING_CASHBOOK = "http://localhost:9201/api/v1/accounting/cashbook";

    private final RestTemplate restTemplate;

    @Override
    public CashBookDtoResponse findById(@NonNull final UUID id) {

        log.info("Client FindById iniciado: {}", id);

        var responseEntity = restTemplate.getForEntity(URI_ACCOUNTING_CASHBOOK + "/" + id, CashBookDtoResponse.class);
        var dtoResponse = responseEntity.getBody();

        log.info("Client FindById concluído: {}", dtoResponse);

        return dtoResponse;
    }
}

