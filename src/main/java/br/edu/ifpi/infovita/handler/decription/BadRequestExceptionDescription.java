package br.edu.ifpi.infovita.handler.decription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class BadRequestExceptionDescription {
    private String timestamps;
    private String exception;
    private String message;
    private Integer status;
}
