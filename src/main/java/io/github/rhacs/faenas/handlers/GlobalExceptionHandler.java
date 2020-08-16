package io.github.rhacs.faenas.handlers;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
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

    // Herencias (ResponseEntityExceptionHandler)
    // -----------------------------------------------------------------------------------------

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());

        // Estado
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

        // Crear respuesta
        ErrorResponse response = new ErrorResponse();
        response.setStatus(responseStatus.value());
        response.setMessage("Error de Validación");

        // Agregar errores al listado
        ex.getBindingResult().getFieldErrors().forEach(response::addError);
        ex.getBindingResult().getGlobalErrors().forEach(response::addError);

        return ResponseEntity.status(responseStatus).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        logger.error(ex.getMessage());

        // Estado
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

        // Crear respuesta
        ErrorResponse response = new ErrorResponse();
        response.setStatus(responseStatus.value());
        response.setMessage("Error de Validación");

        // Agregar errores al listado
        ex.getBindingResult().getFieldErrors().forEach(response::addError);
        ex.getBindingResult().getGlobalErrors().forEach(response::addError);

        return ResponseEntity.status(responseStatus).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());

        // Crear respuesta
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("No se ha encontrado un controlador para las peticiones " + ex.getHttpMethod() + " sobre "
                + ex.getRequestURL());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
