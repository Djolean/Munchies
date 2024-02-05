package com.ingsoftware.munchies.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    private ResponseEntity<Exception.ExceptionResponse> prepareResponse(HttpStatus status, String message) {
        Exception.ExceptionResponse response = new Exception.ExceptionResponse(status, LocalDateTime.now(), message);
        log.info("Exception: {}", response);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> handleRestaurantNotFoundExceptionRest(Exception.GroupOrderNotFoundException ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> handleOrderNotValidExceptionRest(Exception.RestaurantNotFoundException ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> handleBadRequestExceptionRest(Exception.RequestNotFoundException ex) {
        return prepareResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> handleInternalServerErrorExceptionRest(Exception.ServerErrorException ex) {
        return prepareResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> handleDeleteRestaurantWhileOrderActiveRest(Exception.GroupOrderStillActiveException ex) {
        return prepareResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> handleUserNotFoundExceptionRest(Exception.UserNotFoundException ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> handleUserAlreadyExistsExceptionRest(Exception.UserAlreadyExists ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> GroupOrderNotActiveExceptionRest(Exception.GroupOrderNotActiveException ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> UserAlreadyConfirmedRest(Exception.UserAlreadyConfirmed ex) {
        return prepareResponse(HttpStatus.ALREADY_REPORTED, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> UserNotConfirmedYetRest(Exception.UserNotConfirmedYet ex) {
        return prepareResponse(HttpStatus.ALREADY_REPORTED, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Exception.ExceptionResponse> TokenNotValidOrExpiredRest(Exception.TokenNotValidOrExpired ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}


