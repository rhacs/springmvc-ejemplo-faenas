package io.github.rhacs.faenas.modelos;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import io.github.rhacs.faenas.Constantes;

@Entity
@Table(name = Constantes.TABLA_FAENAS)
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = Constantes.SECUENCIA_FAENAS)
public class Faena {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico de la {@link Faena}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_FAENAS, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.FAENA_ID)
    private Long id;

    /**
     * Descripción de la {@link Faena}
     */
    @NotEmpty
    @Size(min = 5)
    @Column(nullable = false)
    private String descripcion;

    /**
     * Comuna de la {@link Faena}
     */
    @NotEmpty
    @Size(min = 5)
    @Column(nullable = false)
    private String comuna;

    /**
     * Fecha de inicio de la {@link Faena}
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate fechaInicio;

    /**
     * Fecha de término de la {@link Faena}
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate fechaTermino;

    @OneToMany(mappedBy = "faena")
    private Set<Detalle> detalles;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Faena}
     */
    public Faena() {
        // Constructor vacío
    }

    /**
     * Crea una nueva instancia del objeto {@link Faena}
     * 
     * @param id           identificador numérico
     * @param descripcion  descripción
     * @param comuna       comuna
     * @param fechaInicio  fecha de inicio
     * @param fechaTermino fecha de término
     * @param detalles     listado de {@link Detalle}s
     */
    public Faena(Long id, @NotEmpty @Size(min = 5) String descripcion, @NotEmpty @Size(min = 5) String comuna,
            @NotNull LocalDate fechaInicio, @NotNull LocalDate fechaTermino, Set<Detalle> detalles) {
        this.id = id;
        this.descripcion = descripcion;
        this.comuna = comuna;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.detalles = detalles;
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
     * @return la comuna
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * @return la fecha de inicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return la fecha de término
     */
    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    /**
     * @return el listado de {@link Detalle}s
     */
    public Set<Detalle> getDetalles() {
        return detalles;
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
     * @param comuna la comuna a establecer
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * @param fechaInicio la fecha de inicio a establecer
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @param fechaTermino la fecha de término a establecer
     */
    public void setFechaTermino(LocalDate fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    /**
     * @param detalles el listado de {@link Detalle}s a establecer
     */
    public void setDetalles(Set<Detalle> detalles) {
        this.detalles = detalles;
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

        Faena other = (Faena) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Faena [id=%s, descripcion=%s, comuna=%s, fechaInicio=%s, fechaTermino=%s, detalle=%s]",
                id, descripcion, comuna, fechaInicio, fechaTermino, detalles);
    }

}
