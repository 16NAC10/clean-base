package curso.input;

import curso.modelo.Curso;

import java.util.List;
import java.util.UUID;

public interface BuscarCursoInput {
    Curso buscarCursoPorId(UUID id);
    Curso buscarCursoPorNombre(String nombre);
    List<Curso> buscarCursos();
}
