package com.nomad.accounting.config.exception;

import com.nomad.accounting.config.exception.http404.ResourceNotFoundException;
import com.nomad.accounting.config.exception.http409.ResourceConflictRulesException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public final class GlobalHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    // ---------- TRATAMENTO DE EXCEÇÕES DEFAULT ---------- //
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                   HttpHeaders httpHeaders,
                                                                   HttpStatusCode httpStatusCode,
                                                                   WebRequest webRequest) {
        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatusCode);
        problemDetail.setType(URI.create("https://nomad.com/errors/invalid-fields"));
        problemDetail.setTitle(getMessage("exception.request.attribute.invalid"));

        var fields = getFields(ex);

        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail, httpHeaders, httpStatusCode, webRequest);
    }

    // ---------- Métodos assessórios ---------- //
    private String getMessage(String messageKey) {
        return this.messageSource.getMessage(messageKey, new Object[]{}, LocaleContextHolder.getLocale());
    }

    private Map<String, String> getFields(BindException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                objectError -> this.messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
    }

    // ---------- TRATAMENTO DE EXCEÇÕES CUSTOMIZADAS ---------- //
    // ---------- 404 Not Found ---------- //
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFoundException ex, WebRequest webRequest) {

        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("https://nomad.com/errors/resource-not-found"));

        var message = messageSource
                .getMessage(ex.getMessageKey(), new Object[]{ex.getId()}, LocaleContextHolder.getLocale());

        problemDetail.setTitle(message);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

    // ---------- 409 Conflict ---------- //
    @ExceptionHandler(ResourceConflictRulesException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceConflictRulesException ex, WebRequest webRequest) {

        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setType(URI.create("https://nomad.com/errors/resource-conflict-rules"));

        var message = messageSource
                .getMessage(ex.getMessageKey(), new Object[]{ex.getId()}, LocaleContextHolder.getLocale());

        problemDetail.setTitle(message);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(problemDetail);
    }
}

