package ar.edu.undec.adapter.service.curso.dto;

import curso.modelo.CursoNivel;

import java.time.LocalDate;
import java.util.UUID;

public class CursoDto {
    private UUID id;
    private String nombre;
    private LocalDate fechaCierreInscripcion;
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
}
