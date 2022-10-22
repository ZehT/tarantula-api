package com.ambitoscala.config;

import com.ambitoscala.commons.MessageSourceService;
import com.ambitoscala.tarantula.exception.TarantulaAlreadyExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestControllerAdviceExceptionHandler {

    private final MessageSourceService messageSourceService;

    public RestControllerAdviceExceptionHandler(final MessageSourceService messageSourceService) {
        this.messageSourceService = messageSourceService;
    }

    @ExceptionHandler(TarantulaAlreadyExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity<BasicResponse<Void>> tarantulaAlreadyExistsException(final TarantulaAlreadyExistsException tarantulaAlreadyExistsException) {
        final BasicResponse<Void> basicResponse = BasicResponse.fail(HttpStatus.CONFLICT.value(), tarantulaAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(basicResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BasicResponse<Map<String, String>>> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = this.messageSourceService.getMessage(error.getDefaultMessage());
            errors.put(fieldName, errorMessage);
        });
        final BasicResponse<Map<String, String>> basicResponse = BasicResponse.fail(HttpStatus.BAD_REQUEST.value(),
                this.messageSourceService.getMessage("commons.validation.mandatory.fields"),
                errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(basicResponse);
    }

}
