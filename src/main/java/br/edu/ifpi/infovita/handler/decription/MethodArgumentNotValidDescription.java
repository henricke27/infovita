package br.edu.ifpi.infovita.handler.decription;


import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MethodArgumentNotValidDescription extends BadRequestExceptionDescription {
    private final String field;
    private final String fieldMessage;
}
