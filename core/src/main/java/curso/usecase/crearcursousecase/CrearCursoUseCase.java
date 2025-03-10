package curso.usecase.crearcursousecase;

import curso.exception.CursoExisteException;
import curso.input.CrearCursoInput;
import curso.modelo.Curso;
import curso.output.CrearCursoRepository;

import java.util.UUID;

public class CrearCursoUseCase implements CrearCursoInput {
    private CrearCursoRepository crearCursoRepository;

    public CrearCursoUseCase(CrearCursoRepository crearCursoRepository) {
        this.crearCursoRepository = crearCursoRepository;
    }

    @Override
    public UUID crearCurso(CrearCursoRequestModel crearCursoRequestModel) throws CursoExisteException {
        if(crearCursoRepository.buscarCurso(crearCursoRequestModel.getNombre())){
            throw new CursoExisteException("El curso ya existe");
        }
        Curso curso = Curso.factory(crearCursoRequestModel.getId(), crearCursoRequestModel.getNombre(), crearCursoRequestModel.getFechaCierreInscripcion(),crearCursoRequestModel.getNivel());
        return crearCursoRepository.crearCurso(curso);
    }
}
