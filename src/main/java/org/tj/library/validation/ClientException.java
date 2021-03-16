package org.tj.library.validation;

import org.springframework.http.HttpStatus;

public class ClientException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "A ClientException was thrown.";
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ClientException(HttpStatus httpStatus) {
        status = httpStatus;
    }

    public ClientException() {
        super(DEFAULT_MESSAGE);
    }

    public ClientException(String message, HttpStatus httpStatus) {
        super(message);
        status = httpStatus;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
