package com.ingsoftware.munchies.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerErrorException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.RestaurantNotFoundException.class)
    public String handleRestaurantNotFoundException(Exception.RestaurantNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/error-404";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.GroupOrderNotFoundException.class)
    public String handleOrderNotValidException(Exception.GroupOrderNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/error-404";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.RequestNotFoundException.class)
    public String handleBadRequestException(Exception.RequestNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/error-400";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerErrorException.class)
    public String handleGenericException(Exception.ServerErrorException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/error-500";
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Exception.GroupOrderStillActiveException.class)
    public String handleDeleteRestaurantWhileOrderActive(Exception.GroupOrderStillActiveException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error/error-403";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.UserNotFoundException.class)
    public String handleUserNotFoundException(Exception.UserNotFoundException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error/error-404";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.UserAlreadyExists.class)
    public String handleUserAlreadyExistsException(Exception.UserAlreadyExists ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error/error-404";
    }
}
