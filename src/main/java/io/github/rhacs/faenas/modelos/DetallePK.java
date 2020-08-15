package io.github.rhacs.faenas.modelos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetallePK implements Serializable {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = -5219485533082362338L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico de la {@link Faena}
     */
    @Column(nullable = false)
    private Long faenaId;

    /**
     * Identificador numérico del {@link Item}
     */
    @Column(nullable = false)
    private Long itemId;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link DetallePK}
     */
    public DetallePK() {
        // Constructor vacío
    }

    /**
     * Crea una nueva instancia del objeto {@link DetallePK}
     * 
     * @param faenaId identificador numérico de la {@link Faena}
     * @param itemId  identificador numérico del {@link Item}
     */
    public DetallePK(Long faenaId, Long itemId) {
        this.faenaId = faenaId;
        this.itemId = itemId;
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
     * @return el identificador numérico de la {@link Faena}
     */
    public Long getFaenaId() {
        return faenaId;
    }

    /**
     * @return el identificador numérico del {@link Item}
     */
    public Long getItemId() {
        return itemId;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param faenaId el identificador numérico de la {@link Faena} a establecer
     */
    public void setFaenaId(Long faenaId) {
        this.faenaId = faenaId;
    }

    /**
     * @param itemId el identificador numérico del {@link Item} a establecer
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(faenaId, itemId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        DetallePK other = (DetallePK) obj;

        return Objects.equals(faenaId, other.faenaId) && Objects.equals(itemId, other.itemId);
    }

    @Override
    public String toString() {
        return String.format("DetallePK [faenaId=%s, itemId=%s]", faenaId, itemId);
    }

}
