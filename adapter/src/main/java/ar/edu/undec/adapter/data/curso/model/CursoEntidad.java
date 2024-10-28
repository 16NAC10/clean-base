package ar.edu.undec.adapter.data.curso.model;

import java.time.LocalDate;
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
}
