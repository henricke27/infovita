package br.edu.ifpi.infovita.exception;

public class EstablishmentNotFoundException extends BadRequestException{
    public EstablishmentNotFoundException(String message){
        super(message);
    }
}
