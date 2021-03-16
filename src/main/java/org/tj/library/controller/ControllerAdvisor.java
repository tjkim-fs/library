package org.tj.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tj.library.validation.ClientException;

@ControllerAdvice
public class ControllerAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleAllException(Exception e) {
        LOGGER.warn("Unexpected exception - message: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler({ClientException.class})
    public ResponseEntity handleClientException(ClientException e) {
        LOGGER.warn("ClientException - status: {}, message: {}", e.getStatus(), e);

        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
