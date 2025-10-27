package br.senac.rj.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(ResourceNotFoundException rnfe) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "O recurso solicitado não foi encontrado");
        body.put("message", rnfe.getMessage());
        body.put("timestamp", LocalDateTime.now());
        body.put("statusCode", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerGenericException(Exception e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Houve um erro no servidor");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        body.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
