package io.github.rhacs.faenas;

public class Constantes {

    // Tablas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la tabla que contiene la información de los {@link Proveedor}es
     */
    public static final String TABLA_PROVEEDORES = "faenas_proveedores";

    /**
     * Nombre de la tabla que contiene la información de los {@link Item}s
     */
    public static final String TABLA_ITEMS = "faenas_items";

    /**
     * Nombre de la tabla que contiene la información de las {@link Faena}s
     */
    public static final String TABLA_FAENAS = "faenas_faenas";

    // Secuencias
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la secuencia responsable de generar los valores para el
     * identificador numérico de los {@link Proveedor}es
     */
    public static final String SECUENCIA_PROVEEDORES = TABLA_PROVEEDORES + "_seq";

    /**
     * Nombre de la secuencia responsable de generar los valores para el
     * identificador numérico de los {@link Item}s
     */
    public static final String SECUENCIA_ITEMS = TABLA_ITEMS + "_seq";

    /**
     * Nombre de la secuencia reponsable de generar los valores para el
     * identificador numérico de las {@link Faena}s
     */
    public static final String SECUENCIA_FAENAS = TABLA_FAENAS + "_seq";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
