package br.edu.ifpi.infovita.handler.decription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
public class GeneralExceptionDescription {
    private LocalDateTime timestamps;
    private String exception;
    private String message;
    private Integer status;
}
