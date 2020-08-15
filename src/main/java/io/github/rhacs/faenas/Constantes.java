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

    /**
     * Nombre de la tabla que contiene la información de la relación entre la tabla
     * de {@link Proveedor}es y la tabla {@link Item}s
     */
    public static final String TABLA_DETALLES = "faenas_detalles";

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

    /**
     * Nombre de la secuencia reponsable de generar los valores para el
     * identificador numérico de los {@link Detalle}s
     */
    public static final String SECUENCIA_DETALLES = TABLA_DETALLES + "_seq";

    // Columnas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre del identificador numérico para la tabla de {@link Proveedor}es
     */
    public static final String PROVEEDOR_ID = "proveedor_id";

    /**
     * Nombre del identificador numérico para la tabla de {@link Item}s
     */
    public static final String ITEM_ID = "item_id";

    /**
     * Nombre del identificador numérico para la tabla de {@link Faena}s
     */
    public static final String FAENA_ID = "faena_id";

    /**
     * Nombre del identificador numérico para la tabla de {@link Detalle}s
     */
    public static final String DETALLE_ID = "detalle_id";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
