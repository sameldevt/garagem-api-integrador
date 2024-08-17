package br.com.almavivasolutions.integrador.model.exception;

import java.io.Serial;

public class InvalidHeaderException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 2L;

    public InvalidHeaderException(String s) {
        super(s);
    }
}
