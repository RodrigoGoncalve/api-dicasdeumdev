package br.com.dicasdeumdev.api.resources.exceptions;

import br.com.dicasdeumdev.api.services.excepyions.DataIntergratyViolationException;
import br.com.dicasdeumdev.api.services.excepyions.ObjectNotFoundexception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundexception.class)
    public ResponseEntity<StanderError> objectNoutFound(ObjectNotFoundexception ex, HttpServletRequest request){
        StanderError error = new StanderError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntergratyViolationException.class)
    public ResponseEntity<StanderError> datIntegrityViolationException(DataIntergratyViolationException ex, HttpServletRequest request){
        StanderError error = new StanderError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
