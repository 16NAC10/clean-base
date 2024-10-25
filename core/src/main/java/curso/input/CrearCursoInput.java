package curso.input;

import curso.exception.CursoExisteException;
import curso.usecase.crearcursousecase.CrearCursoRequestModel;

import java.util.UUID;

public interface CrearCursoInput {
    UUID crearCurso(CrearCursoRequestModel curso) throws CursoExisteException;
}
