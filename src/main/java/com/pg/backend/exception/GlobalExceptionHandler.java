package com.pg.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.pg.backend.constants.ApplicationConstants.CLIENT_EXCEPTION_STATUS_CODE;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleClientException(ClientException clientException) {
        ErrorResponse response = ErrorResponse
                .builder()
                .status(CLIENT_EXCEPTION_STATUS_CODE)
                .message(clientException.getMessage())
                .response(clientException.getResponse())
                .build();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
