package com.ingsoftware.munchies.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public interface Exception {
    class UserNotFoundException extends RuntimeException {

        public UserNotFoundException() {
            super("User not found!");
        }
    }

    class UserAlreadyExists extends RuntimeException {

        public UserAlreadyExists() {
            super("User already exists!");
        }
    }

    class GroupOrderNotFoundException extends RuntimeException {

        public GroupOrderNotFoundException() {super("Group order not found!"); }
    }

    class RestaurantNotFoundException extends RuntimeException {

        public RestaurantNotFoundException() {
            super("Restaurant not found!");
        }
    }

    class GroupOrderStillActiveException extends RuntimeException {

        public GroupOrderStillActiveException() {
            super("Group order is still active!");
        }
    }
    class RequestNotFoundException extends RuntimeException {

        public RequestNotFoundException() {
            super("Bad request, endpoint doesn't exist!");
        }
    }
    class ServerErrorException extends RuntimeException {

        public ServerErrorException() {
            super("Internal server error!");
        }
    }
    class GroupOrderNotActiveException extends RuntimeException {

        public GroupOrderNotActiveException() {
            super("Group order not active!");
        }
    }
    class UserAlreadyConfirmed extends RuntimeException {

        public UserAlreadyConfirmed() {
            super("User is already confirmed!");
        }
    }
    class UserNotConfirmedYet extends RuntimeException {

        public UserNotConfirmedYet() {
            super("User is not confirmed yet!");
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ExceptionResponse {
        private HttpStatus httpStatus;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        private LocalDateTime timestamp;
        private String message;
    }

}
