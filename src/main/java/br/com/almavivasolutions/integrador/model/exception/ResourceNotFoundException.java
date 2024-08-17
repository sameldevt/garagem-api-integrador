package br.com.almavivasolutions.integrador.model.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String s) {
        super(s);
    }
}
