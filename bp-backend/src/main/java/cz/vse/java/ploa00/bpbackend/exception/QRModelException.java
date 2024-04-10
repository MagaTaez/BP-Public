package cz.vse.java.ploa00.bpbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QRModelException extends RuntimeException{

     public QRModelException(String message) {super(message);}
}
