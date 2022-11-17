package br.edu.ifpi.infovita.handler;

import br.edu.ifpi.infovita.exception.BadRequestException;
import br.edu.ifpi.infovita.handler.decription.BadRequestExceptionDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<BadRequestExceptionDescription> badRequestExceptionHandler(BadRequestException ex){
        BadRequestExceptionDescription description = BadRequestExceptionDescription.builder()
                .timestamps(LocalDateTime.now())
                .exception(ex.getClass().getSimpleName())
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(description, HttpStatus.BAD_REQUEST);
    }

}
