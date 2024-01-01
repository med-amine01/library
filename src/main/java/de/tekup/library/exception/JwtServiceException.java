package de.tekup.library.exception;

public class JwtServiceException extends RuntimeException {
    public JwtServiceException(String message) {
        super(message);
    }
}
