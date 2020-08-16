package io.github.rhacs.faenas.modelos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "error")
public class ErrorResponse {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Marca de tiempo que indica el momento exacto cuando ocurrió el error
     */
    @JsonFormat(pattern = "", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    /**
     * Estado del error
     */
    private int status;

    /**
     * Mensaje que detalla el error
     */
    private String message;

    /**
     * Listado de errores
     */
    private Set<ErrorDetail> errors;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link ErrorResponse}
     */
    public ErrorResponse() {
        timestamp = LocalDateTime.now();
        errors = new HashSet<>();
    }

    /**
     * Crea una nueva instancia del objeto {@link ErrorResponse}
     * 
     * @param status  código del estado HTTP
     * @param message el detalle del error
     * @param errors  listado de errores
     */
    public ErrorResponse(int status, String message, Set<ErrorDetail> errors) {
        this();

        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega un nuevo error al listado de errores
     * 
     * @param campo          nombre del campo donde ocurrió el error
     * @param mensaje        detalle del error
     * @param valorRechazado valor rechazado
     * @return {@code true} si el error fue agregado, {@code false} en cualquier
     *         otro caso
     */
    public boolean addError(String campo, String mensaje, Object valorRechazado) {
        return errors.add(new ErrorDetail(campo, mensaje, valorRechazado));
    }

    /**
     * Agrega un {@link FieldError} al listado
     * 
     * @param e objeto {@link FieldError} con la información del error
     * @return {@code true} si el error fue agregado, {@code false} en cualquier
     *         otro caso
     */
    public boolean addError(FieldError e) {
        return errors.add(new ErrorDetail(e.getField(), e.getDefaultMessage(), e.getRejectedValue()));
    }

    /**
     * Agrega un {@link ObjectError} al listado
     * 
     * @param e objeto {@link ObjectError} con la información del error
     * @return {@code true} si el error fue agregado, {@code false} en cualquier
     *         otro caso
     */
    public boolean addError(ObjectError e) {
        return errors.add(new ErrorDetail(e.getObjectName(), e.getDefaultMessage(), null));
    }

    /**
     * Agrega una {@link ConstraintViolation} al listado
     * 
     * @param e objeto {@link ConstraintViolation} con la información del error
     * @return {@code true} si el error fue agregado, {@code false} en cualquier
     *         otro caso
     */
    public boolean addError(ConstraintViolation<?> e) {
        return errors.add(new ErrorDetail(((PathImpl) e.getPropertyPath()).getLeafNode().asString(), e.getMessage(),
                e.getInvalidValue()));
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el momento exacto cuando ocurrió el error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return el código de estado HTTP
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return el detalle del error
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return el listado de errores
     */
    public Set<ErrorDetail> getErrors() {
        return errors;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param timestamp la marca de tiempo a establecer
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @param status el código de estado HTTP a establecer
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param message el detalle a establecer
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param errors el listado de errores a establecer
     */
    public void setErrors(Set<ErrorDetail> errors) {
        this.errors = errors;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("ErrorResponse [timestamp=%s, status=%s, message=%s, errors=%s]", timestamp, status,
                message, errors);
    }

}
