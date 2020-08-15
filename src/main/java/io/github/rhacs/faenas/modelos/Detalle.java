package io.github.rhacs.faenas.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.github.rhacs.faenas.Constantes;

@Entity
@Table(name = Constantes.TABLA_DETALLES)
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = Constantes.SECUENCIA_DETALLES)
public class Detalle {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Detalle}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_DETALLES, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.DETALLE_ID)
    private Long id;

    /**
     * Cantidad que se utilizará del {@link Item}
     */
    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private Long cantidad;

    /**
     * {@link Faena} a la que está relacionada el {@link Detalle}
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = Constantes.FAENA_ID)
    private Faena faena;

    /**
     * {@link Item} al que está relacionado el {@link Detalle}
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = Constantes.ITEM_ID)
    private Item item;

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
     * @param id       identificador numérico
     * @param cantidad cantidad
     * @param faena    {@link Faena}
     * @param item     {@link Item}
     */
    public Detalle(Long id, @NotNull @Min(1) Long cantidad, Faena faena, Item item) {
        this.id = id;
        this.cantidad = cantidad;
        this.faena = faena;
        this.item = item;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico
     */
    public Long getId() {
        return id;
    }

    /**
     * @return la cantidad
     */
    public Long getCantidad() {
        return cantidad;
    }

    /**
     * @return la {@link Faena}
     */
    public Faena getFaena() {
        return faena;
    }

    /**
     * @return el {@link Item}
     */
    public Item getItem() {
        return item;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador numérico a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param cantidad la cantidad a establecer
     */
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
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
        return String.format("Detalle [id=%s, cantidad=%s]", id, cantidad, faena, item);
    }

}
