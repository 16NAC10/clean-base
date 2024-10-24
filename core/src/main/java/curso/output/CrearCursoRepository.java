package curso.output;

import curso.modelo.Curso;

import java.util.UUID;

public interface CrearCursoRepository {
    UUID crearCurso(Curso curso);
    boolean buscarCurso(UUID id);
}
