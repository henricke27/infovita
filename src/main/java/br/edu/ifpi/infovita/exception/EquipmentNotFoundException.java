package br.edu.ifpi.infovita.exception;

public class EquipmentNotFoundException extends BadRequestException{
    public EquipmentNotFoundException(String message){
        super(message);
    }
}
