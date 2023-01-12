package br.springapi.springapi.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException{

    public DataIntegratyViolationException(String message) {

        super(message);
    }
}
