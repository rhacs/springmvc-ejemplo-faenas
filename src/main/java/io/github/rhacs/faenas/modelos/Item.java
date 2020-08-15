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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.github.rhacs.faenas.Constantes;

@Entity
@Table(name = Constantes.TABLA_ITEMS)
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = Constantes.SECUENCIA_ITEMS)
public class Item {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Item}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_ITEMS, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.ITEM_ID)
    private Long id;

    /**
     * Descripción del {@link Item}
     */
    @NotEmpty
    @Size(min = 5)
    @Column(nullable = false)
    private String descripcion;

    /**
     * Cantidad en el inventario del {@link Item}
     */
    @NotNull
    @Min(value = 0)
    @Column(nullable = false)
    private Long stock;

    /**
     * Precio unitario del {@link Item}
     */
    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private Long precioUnitario;

    /**
     * {@link Proveedor} del {@link Item}
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = Constantes.PROVEEDOR_ID)
    private Proveedor proveedor;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Item}
     */
    public Item() {
        // Constructor vacío
    }

    /**
     * Crea una nueva instancia del objeto {@link Item}
     * 
     * @param id             identificador numérico
     * @param descripcion    descripción
     * @param stock          cantidad restante en el inventario
     * @param precioUnitario precio unitario
     * @param proveedor      {@link Proveedor}
     */
    public Item(Long id, @NotEmpty @Size(min = 5) String descripcion, @NotNull @Min(0) Long stock,
            @NotNull @Min(1) Long precioUnitario, Proveedor proveedor) {
        this.id = id;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.proveedor = proveedor;
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
     * @return la descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return la cantidad restante en el inventario
     */
    public Long getStock() {
        return stock;
    }

    /**
     * @return el precio unitario
     */
    public Long getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @return el {@link Proveedor}
     */
    public Proveedor getProveedor() {
        return proveedor;
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
     * @param descripcion la descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param stock la cantidad restante en el inventario a establecer
     */
    public void setStock(Long stock) {
        this.stock = stock;
    }

    /**
     * @param precioUnitario el precio unitario a establecer
     */
    public void setPrecioUnitario(Long precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @param proveedor el {@link Proveedor} a establecer
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

        Item other = (Item) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Item [id=%s, descripcion=%s, stock=%s, precioUnitario=%s, proveedor=%s]", id, descripcion,
                stock, precioUnitario, proveedor);
    }

}
