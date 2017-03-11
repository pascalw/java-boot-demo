package nl.kabisa.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ApplicationController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        List<Error> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Error(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("Validation error", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    protected static class ErrorResponse {
        public final String message;
        public final List<Error> errors;

        protected ErrorResponse(String message, List<Error> errors) {
            this.message = message;
            this.errors = errors;
        }

        protected ErrorResponse(String message) {
            this(message, Collections.emptyList());
        }
    }

    protected static class Error {
        public final String field;
        public final String message;

        protected Error(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
