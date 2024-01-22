package com.ingsoftware.munchies.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public interface Exception {
    class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(String message) {
            super(message);
        }

        public UserNotFoundException() {
            super("User not found!");
        }
    }

    class UserAlreadyExists extends RuntimeException {
        public UserAlreadyExists(String message) {
            super(message);
        }

        public UserAlreadyExists() {
            super("User already exists!");
        }
    }

    class GroupOrderNotFoundException extends RuntimeException {
        public GroupOrderNotFoundException(String message) {
            super(message);
        }

        public GroupOrderNotFoundException() {
            super("Group order not found!");
        }
    }

    class RestaurantNotFoundException extends RuntimeException {
        public RestaurantNotFoundException(String message) {
            super(message);
        }

        public RestaurantNotFoundException() {
            super("Restaurant not found!");
        }
    }

    class GroupOrderStillActiveException extends RuntimeException {
        public GroupOrderStillActiveException(String message) {
            super(message);
        }

        public GroupOrderStillActiveException() {
            super("Group order is still active!");
        }
    }
    class RequestNotFoundException extends RuntimeException {
        public RequestNotFoundException(String message) {
            super(message);
        }

        public RequestNotFoundException() {
            super("Bad request, endpoint doesn't exist!");
        }
    }
    class ServerErrorException extends RuntimeException {
        public ServerErrorException(String message) {
            super(message);
        }

        public ServerErrorException() {
            super("Internal server error!");
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ExceptionResponse {
        private HttpStatus httpStatus;
        private LocalDateTime timestamp;
        private String message;
    }

}
