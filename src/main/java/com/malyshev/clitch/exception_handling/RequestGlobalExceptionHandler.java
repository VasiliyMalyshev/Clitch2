package com.malyshev.clitch.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RequestGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AlreadySent> handleException (AlreadySentException exception) {
        AlreadySent sent = new AlreadySent();
        sent.setInfo(exception.getMessage());
        return new ResponseEntity<>(sent, HttpStatus.BAD_REQUEST);
    }
}