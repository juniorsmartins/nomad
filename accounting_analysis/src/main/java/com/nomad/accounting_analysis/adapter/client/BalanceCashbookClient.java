package com.nomad.accounting_analysis.adapter.client;

import com.nomad.accounting_analysis.adapter.dto.in.CashbookDtoResponse;
import com.nomad.accounting_analysis.application.port.output.BalanceCashbookOutputPort;
import com.nomad.accounting_analysis.config.exception.http404.CashbookNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

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
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        log.warn("ID de Cashbook não encontrado: {}", id);
                        return Mono.error(new CashbookNotFoundException(id));
                    }
                    log.error("Falha interna ao consultar ID do Cashbook: {}", id, ex);
                    return Mono.error(new RuntimeException()); // Propaga outras exceções
                })
                .block();

        log.info("Client FindById concluído: {}", dtoResponse);

        return dtoResponse;
    }
}

