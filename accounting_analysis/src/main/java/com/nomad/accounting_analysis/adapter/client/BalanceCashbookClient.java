package com.nomad.accounting_analysis.adapter.client;

import com.nomad.accounting_analysis.adapter.dto.in.CashbookDtoResponse;
import com.nomad.accounting_analysis.application.port.output.BalanceCashbookOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceCashbookClient implements BalanceCashbookOutputPort {

    private final WebClient webClient;

    @Override
    public CashbookDtoResponse findById(@NonNull final UUID id) {

        log.info("Client FindById iniciado: {}", id);

        var dtoResponse = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CashbookDtoResponse.class)
                .block();

        log.info("Client FindById concluído: {}", dtoResponse);

        return dtoResponse;
    }
}

