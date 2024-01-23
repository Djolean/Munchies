package com.ingsoftware.munchies.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //////EXCEPTIONS FOR MVC
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(Exception.RestaurantNotFoundException.class)
//    public String handleRestaurantNotFoundException(Exception.RestaurantNotFoundException ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error/error-404";
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(Exception.GroupOrderNotFoundException.class)
//    public String handleOrderNotValidException(Exception.GroupOrderNotFoundException ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error/error-404";
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(Exception.RequestNotFoundException.class)
//    public String handleBadRequestException(Exception.RequestNotFoundException ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error/error-400";
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(ServerErrorException.class)
//    public String handleInternalServerErrorException(Exception.ServerErrorException ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error/error-500";
//    }
//
//
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(Exception.GroupOrderStillActiveException.class)
//    public String handleDeleteRestaurantWhileOrderActive(Exception.GroupOrderStillActiveException ex, Model model){
//        model.addAttribute("error", ex.getMessage());
//        return "error/error-403";
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(Exception.UserNotFoundException.class)
//    public String handleUserNotFoundException(Exception.UserNotFoundException ex, Model model){
//        model.addAttribute("error", ex.getMessage());
//        return "error/error-404";
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(Exception.UserAlreadyExists.class)
//    public String handleUserAlreadyExistsException(Exception.UserAlreadyExists ex, Model model){
//        model.addAttribute("error", ex.getMessage());
//        return "error/error-404";
//    }


    ////EXCEPTIONS FOR REST
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
    public ResponseEntity<Exception.ExceptionResponse> handleUserAlreadyExistsException(Exception.UserAlreadyExists ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
