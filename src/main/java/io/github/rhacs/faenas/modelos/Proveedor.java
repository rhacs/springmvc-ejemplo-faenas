package io.github.rhacs.faenas.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.github.rhacs.faenas.Constantes;

@Entity
@Table(name = Constantes.TABLA_PROVEEDORES)
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = Constantes.SECUENCIA_PROVEEDORES)
public class Proveedor {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Proveedor}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_PROVEEDORES, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.PROVEEDOR_ID)
    private Long id;

    /**
     * Nombre o Razón Social del {@link Proveedor}
     */
    @NotEmpty
    @Size(min = 5)
    @Column(nullable = false)
    private String nombre;

    /**
     * Rol Único Tributario, sin dígito verificador, del {@link Proveedor}
     */
    @NotNull
    @Min(value = 8)
    @Max(value = 8)
    @Column(nullable = false, unique = true)
    private Long rut;

    /**
     * Dirección de la casa matriz del {@link Proveedor}
     */
    @NotEmpty
    @Size(min = 5)
    @Column(nullable = false)
    private String direccion;

    /**
     * Comuna del {@link Proveedor}
     */
    @NotEmpty
    @Size(min = 5)
    @Column(nullable = false)
    private String comuna;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Proveedor}
     */
    public Proveedor() {
        // Constructor vacío
    }

    /**
     * Crea una nueva instancia del objeto {@link Proveedor}
     * 
     * @param id        identificador numérico
     * @param nombre    nombre o razón social
     * @param rut       rol único tributario, sin dígito verificador
     * @param direccion dirección de la casa matriz
     * @param comuna    comuna de la dirección
     */
    public Proveedor(Long id, @NotNull @Size(min = 5) String nombre, @NotNull @Min(8) @Max(8) Long rut,
            @NotNull @Size(min = 5) String direccion, @NotNull @Size(min = 5) String comuna) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.comuna = comuna;
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
     * @return el nombre o razón social
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return el rol único tributario, sin dígito verificador
     */
    public Long getRut() {
        return rut;
    }

    /**
     * @return la dirección de la casa matriz
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return la comuna
     */
    public String getComuna() {
        return comuna;
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
     * @param nombre el nombre o razón social a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param rut el rol único tributario, sin dígito verificador, a establecer
     */
    public void setRut(Long rut) {
        this.rut = rut;
    }

    /**
     * @param direccion la dirección de la casa matriz a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param comuna la comuna a establecer
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id, rut);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Proveedor other = (Proveedor) obj;

        return Objects.equals(id, other.id) && Objects.equals(rut, other.rut);
    }

    @Override
    public String toString() {
        return String.format("Proveedor [id=%s, nombre=%s, rut=%s, direccion=%s, comuna=%s]", id, nombre, rut,
                direccion, comuna);
    }

}
