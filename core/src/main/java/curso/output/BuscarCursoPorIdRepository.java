package curso.output;

import curso.modelo.Curso;

import java.util.UUID;

public interface BuscarCursoPorIdRepository {
    Curso buscarCursoPorId(UUID id);
}