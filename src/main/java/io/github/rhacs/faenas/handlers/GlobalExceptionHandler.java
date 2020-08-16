package io.github.rhacs.faenas.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.rhacs.faenas.excepciones.ElementNotFoundException;
import io.github.rhacs.faenas.modelos.ErrorResponse;

@RestControllerAdvice(basePackages = { "io.github.rhacs.faenas.api" })
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja las excepciones {@link ElementNotFoundException}
     * 
     * @param e objeto {@link ElementNotFoundException} que contiene la información
     *          del error
     * @return un objeto {@link ResponseEntity} con la respuesta
     */
    @ExceptionHandler(value = ElementNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleElementNotFoundException(ElementNotFoundException e) {
        return ResponseEntity.status(e.getResponse().getStatus()).body(e.getResponse());
    }

}
