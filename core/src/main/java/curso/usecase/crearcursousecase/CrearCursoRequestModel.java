package curso.usecase.crearcursousecase;

import curso.modelo.CursoNivel;

import java.time.LocalDate;
import java.util.UUID;

public class CrearCursoRequestModel {
    private UUID id;
    private String nombre;
    private LocalDate fechaCierreInscripcion;
    private CursoNivel nivel;

    public CrearCursoRequestModel() {
    }

    private CrearCursoRequestModel(UUID id, String nombre, LocalDate fechaCierreInscripcion, CursoNivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.nivel = nivel;
    }

    public static CrearCursoRequestModel factory(UUID id, String nombre, LocalDate fechaCierreInscripcion, CursoNivel nivel) {
        try{
            CrearCursoRequestModel crearCursoRequestModel = new CrearCursoRequestModel(id, nombre, fechaCierreInscripcion, nivel);
            return crearCursoRequestModel;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear instancia de CrearCursoRequestModel");
        }
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
