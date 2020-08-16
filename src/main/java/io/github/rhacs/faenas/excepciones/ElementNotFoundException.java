package io.github.rhacs.faenas.excepciones;

import io.github.rhacs.faenas.modelos.ErrorResponse;

public class ElementNotFoundException extends RuntimeException {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = -9066984374778593317L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link ErrorResponse} que contiene la información del error
     */
    private final transient ErrorResponse response;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link ElementNotFoundException}
     *
     * @param response
     */
    public ElementNotFoundException(ErrorResponse response) {
        super(response.getMessage());

        this.response = response;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el número de serie de la clase
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return el objeto {@link ErrorResponse} que contiene la información del error
     */
    public ErrorResponse getResponse() {
        return response;
    }

}
