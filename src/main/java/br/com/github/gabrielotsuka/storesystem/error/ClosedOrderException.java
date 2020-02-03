package br.com.github.gabrielotsuka.storesystem.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ClosedOrderException extends RuntimeException {
    public ClosedOrderException(String s) {
        super(s);
    }
}
