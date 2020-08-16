package io.github.rhacs.faenas.modelos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_EMPTY)
public class ErrorDetail {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre del campo donde ocurrió el error
     */
    private String field;

    /**
     * Detalle del error
     */
    private String message;

    /**
     * Valor rechazado
     */
    private Object rejectedValue;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link ErrorDetail}
     */
    public ErrorDetail() {

    }

    /**
     * Crea una nueva instancia del objeto {@link ErrorDetail}
     * 
     * @param message detalle del error
     */
    public ErrorDetail(String message) {
        this.message = message;
    }

    /**
     * Crea una nueva instancia del objeto {@link ErrorDetail}
     * 
     * @param field         nombre del campo donde ocurrió el error
     * @param message       detalle del error
     * @param rejectedValue valor rechazado por el sistema
     */
    public ErrorDetail(String field, String message, Object rejectedValue) {
        this(message);

        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el nombre del campo donde ocurrió el error
     */
    public String getField() {
        return field;
    }

    /**
     * @return el detalle del error
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return el valor rechazado
     */
    public Object getRejectedValue() {
        return rejectedValue;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param field el nombre del campo a establecer
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @param message el detalle a establecer
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param rejectedValue el valor rechazado a establecer
     */
    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("ErrorDetail [field=%s, message=%s, rejectedValue=%s]", field, message, rejectedValue);
    }

}
