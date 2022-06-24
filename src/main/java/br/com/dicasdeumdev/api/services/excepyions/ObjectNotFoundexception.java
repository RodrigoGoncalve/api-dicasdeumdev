package br.com.dicasdeumdev.api.services.excepyions;

public class ObjectNotFoundexception extends RuntimeException{

    public ObjectNotFoundexception(String message) {
        super(message);
    }
}
