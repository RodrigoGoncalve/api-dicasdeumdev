package br.com.dicasdeumdev.api.services.excepyions;

public class DataIntergratyViolationException extends RuntimeException{

    public DataIntergratyViolationException(String message) {
        super(message);
    }
}
