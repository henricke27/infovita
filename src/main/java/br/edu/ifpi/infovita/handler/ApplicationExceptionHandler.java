package br.edu.ifpi.infovita.handler;

import br.edu.ifpi.infovita.exception.BadRequestException;
import br.edu.ifpi.infovita.handler.decription.BadRequestExceptionDescription;
import br.edu.ifpi.infovita.handler.decription.MethodArgumentNotValidDescription;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String fields = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining("; "));

        String fieldsMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        MethodArgumentNotValidDescription description = MethodArgumentNotValidDescription.builder()
                .timestamps(LocalDateTime.now())
                .exception(ex.getClass().getSimpleName())
                .message("Exception to be thrown when validation")
                .status(status.value())
                .field(fields)
                .fieldMessage(fieldsMessage)
                .build();

        return new ResponseEntity<>(description, status);
    }
}
