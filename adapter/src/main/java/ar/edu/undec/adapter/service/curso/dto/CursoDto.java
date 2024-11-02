package ar.edu.undec.adapter.service.curso.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import curso.modelo.CursoNivel;

import java.time.LocalDate;
import java.util.UUID;

public class CursoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("fecha_cierre_inscripcion")
    private LocalDate fechaCierreInscripcion;
    @JsonProperty("nivel")
    private CursoNivel nivel;

    public CursoDto() {}

    private CursoDto(UUID id, String nombre, LocalDate fechaCierreInscripcion, CursoNivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.nivel = nivel;
    }

    public static CursoDto factory(UUID id, String nombre, LocalDate fechaCierreInscripcion, CursoNivel nivel) {
        return new CursoDto(id, nombre, fechaCierreInscripcion, nivel);
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
