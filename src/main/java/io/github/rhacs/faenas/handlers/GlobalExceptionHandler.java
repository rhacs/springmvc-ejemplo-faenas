package io.github.rhacs.faenas.handlers;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.rhacs.faenas.excepciones.ElementNotFoundException;
import io.github.rhacs.faenas.modelos.ErrorResponse;

@RestControllerAdvice(basePackages = { "io.github.rhacs.faenas.api" })
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link Logger} que contiene los métodos de depuración
     */
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Maneja las excepciones {@link ElementNotFoundException}
     * 
     * @param e objeto {@link ElementNotFoundException} que contiene la información
     *          del error
     * @return un objeto {@link ResponseEntity} con la respuesta
     */
    @ExceptionHandler(value = ElementNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleElementNotFound(ElementNotFoundException e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(e.getResponse().getStatus()).body(e.getResponse());
    }

    /**
     * Maneja las excepciones {@link ConstraintViolationException} (Validación)
     * 
     * @param e objeto {@link ConstraintViolationException} con la información de
     *          el/los errores
     * @return un objeto {@link ResponseEntity} con la respuesta
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e) {
        logger.error(e.getMessage());

        // Estado Http
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Crear respuesta
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setMessage("Violación de restricción. Error de validación");

        // Agregar errores de validación
        e.getConstraintViolations().forEach(response::addError);

        return ResponseEntity.status(status).body(response);
    }

}
