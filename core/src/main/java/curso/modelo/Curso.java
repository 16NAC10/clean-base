package curso.modelo;

import curso.exception.CursoIncompletoException;

import java.time.LocalDate;
import java.util.UUID;

public class Curso {
    private UUID id;
    private String nombre;
    private LocalDate fechaCierreInscripcion;
    private CursoNivel nivel;

    private Curso(UUID id, String nombre, LocalDate fechaCierreInscripcion, CursoNivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.nivel = nivel;
    }

    public static Curso factory(UUID id, String nombre, LocalDate fechaCierreInscripcion,CursoNivel nivel) throws CursoIncompletoException {
        if(id == null){
            id = UUID.randomUUID();
        }
        if(nombre == null || nombre.isEmpty()){
            throw new CursoIncompletoException("El nombre del curso es incorrecto");
        }
        if(fechaCierreInscripcion == null || fechaCierreInscripcion.isBefore(LocalDate.now())){
            throw new CursoIncompletoException("La fecha de cierre de inscripción del curso no es válida");
        }
        if(nivel == null){
            throw new CursoIncompletoException("El nivel del curso no puede ser nulo");
        }
        return new Curso(id, nombre, fechaCierreInscripcion, nivel);
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
