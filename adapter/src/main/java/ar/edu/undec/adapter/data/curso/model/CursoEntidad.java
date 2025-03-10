package ar.edu.undec.adapter.data.curso.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import curso.modelo.CursoNivel;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "cursos")
public class CursoEntidad {
    @Id
    private UUID id;
    private String nombre;
    private LocalDate fechaCierreInscripcion;
    private CursoNivel nivel;

    public CursoEntidad() {}

    public CursoEntidad(UUID id, String nombre, LocalDate fechaCierreInscripcion, CursoNivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.nivel = nivel;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaCierreInscripcion() {
        return fechaCierreInscripcion;
    }

    public CursoNivel getNivel() {
        return nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoEntidad that = (CursoEntidad) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaCierreInscripcion, that.fechaCierreInscripcion) && nivel == that.nivel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaCierreInscripcion, nivel);
    }
}
