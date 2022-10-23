package com.ambitoscala.config;

import com.ambitoscala.commons.MessageSourceService;
import com.ambitoscala.tarantula.exception.MoltsDontHappenThatFastException;
import com.ambitoscala.tarantula.exception.NoContentException;
import com.ambitoscala.tarantula.exception.TarantulaAlreadyExistsException;
import com.ambitoscala.tarantula.exception.TarantulaNotFoundException;
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

    @ExceptionHandler(TarantulaNotFoundException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<BasicResponse<Void>> tarantulaNotFoundException(final TarantulaNotFoundException tarantulaNotFoundException) {
        final String errorMessage = messageSourceService.getMessage(tarantulaNotFoundException.getMessage());
        final BasicResponse<Void> basicResponse = BasicResponse.fail(HttpStatus.UNPROCESSABLE_ENTITY.value(), errorMessage);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(basicResponse);
    }

    @ExceptionHandler(MoltsDontHappenThatFastException.class)
    @ResponseStatus(code = HttpStatus.TOO_EARLY)
    public ResponseEntity<BasicResponse<Void>> moltsDontHappenThatFastException(final MoltsDontHappenThatFastException moltsDontHappenThatFastException) {
        final String errorMessage = messageSourceService.getMessage(moltsDontHappenThatFastException.getMessage());
        final BasicResponse<Void> basicResponse = BasicResponse.fail(HttpStatus.TOO_EARLY.value(), errorMessage);
        return ResponseEntity.status(HttpStatus.TOO_EARLY).body(basicResponse);
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<BasicResponse<Void>> noContentException(final NoContentException noContentException) {
        final String errorMessage = messageSourceService.getMessage(noContentException.getMessage());
        final BasicResponse<Void> basicResponse = BasicResponse.fail(HttpStatus.OK.value(), errorMessage);
        return ResponseEntity.status(HttpStatus.OK).body(basicResponse);
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
