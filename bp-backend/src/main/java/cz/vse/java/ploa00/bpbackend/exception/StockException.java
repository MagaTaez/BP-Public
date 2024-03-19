package cz.vse.java.ploa00.bpbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StockException extends RuntimeException{

    public StockException(String message) {super(message);}
}
