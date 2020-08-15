package io.github.rhacs.faenas.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.github.rhacs.faenas.Constantes;

@Entity
@Table(name = Constantes.TABLA_DETALLES)
public class Detalle {

    // Atributos
    // -----------------------------------------------------------------------------------------

    @EmbeddedId
    private DetallePK id;

    /**
     * Información de la {@link Faena}
     */
    @ManyToOne(optional = false)
    @MapsId(value = Constantes.FAENA_ID)
    @JoinColumn(name = Constantes.FAENA_ID)
    private Faena faena;

    /**
     * Información del {@link Item}
     */
    @ManyToOne(optional = false)
    @MapsId(value = Constantes.ITEM_ID)
    @JoinColumn(name = Constantes.ITEM_ID)
    private Item item;

    /**
     * Cantidad del {@link Item}
     */
    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private Long cantidad;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Detalle}
     */
    public Detalle() {
        // Constructor vacío
    }

    /**
     * Crea una nueva instancia del objeto {@link Detalle}
     * 
     * @param id
     * @param faena    información de la {@link Faena}
     * @param item     información del {@link Item}
     * @param cantidad cantidad
     */
    public Detalle(DetallePK id, Faena faena, Item item, @NotNull @Min(1) Long cantidad) {
        this.id = id;
        this.faena = faena;
        this.item = item;
        this.cantidad = cantidad;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el id
     */
    public DetallePK getId() {
        return id;
    }

    /**
     * @return la información de la {@link Faena}
     */
    public Faena getFaena() {
        return faena;
    }

    /**
     * @return la información del {@link Item}
     */
    public Item getItem() {
        return item;
    }

    /**
     * @return la cantidad
     */
    public Long getCantidad() {
        return cantidad;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador a establecer
     */
    public void setId(DetallePK id) {
        this.id = id;
    }

    /**
     * @param faena la {@link Faena} a establecer
     */
    public void setFaena(Faena faena) {
        this.faena = faena;
    }

    /**
     * @param item el {@link Item} a establecer
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @param cantidad la cantidad a establecer
     */
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Detalle other = (Detalle) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Detalle [id=%s, faena=%s, item=%s, cantidad=%s]", id, faena, item, cantidad);
    }

}
