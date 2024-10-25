package curso.input;

import curso.modelo.Curso;

import java.util.UUID;

public interface BuscarCursoPorIdInput {
    Curso buscarCursoPorId(UUID id);
}
