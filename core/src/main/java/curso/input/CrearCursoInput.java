package curso.input;

import curso.exception.CursoExisteException;
import curso.usecase.crearcursousecase.CrearCursoRequestModel;

import java.util.UUID;

public interface CrearCursoInput {
    Integer crearCurso(CrearCursoRequestModel curso) throws CursoExisteException;
}
