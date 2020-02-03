package br.com.github.gabrielotsuka.storesystem.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughProductsException extends RuntimeException{
    public NotEnoughProductsException(String s){
        super(s);
    }
}
