package curso.usecase.crearcursousecase;

import curso.exception.CursoExisteException;
import curso.input.CrearCursoInput;
import curso.modelo.Curso;
import curso.output.BuscarCursoPorIdRepository;
import curso.output.CrearCursoRepository;

public class CrearCursoUseCase implements CrearCursoInput {
    private CrearCursoRepository crearCursoRepository;
    private BuscarCursoPorIdRepository buscarCursoPorIdRepository;

    public CrearCursoUseCase(CrearCursoRepository crearCursoRepository, BuscarCursoPorIdRepository buscarCursoPorIdRepository) {
        this.crearCursoRepository = crearCursoRepository;
        this.buscarCursoPorIdRepository = buscarCursoPorIdRepository;
    }

    @Override
    public Integer crearCurso(CrearCursoRequestModel crearCursoRequestModel) throws CursoExisteException {
        if(crearCursoRepository.buscarCurso(crearCursoRequestModel.getId())){
            throw new CursoExisteException("El curso ya existe");
        }
        Curso curso = Curso.factory(crearCursoRequestModel.getId(), crearCursoRequestModel.getNombre(), crearCursoRequestModel.getFechaCierreInscripcion(),crearCursoRequestModel.getNivel());
        return crearCursoRepository.crearCurso(curso);
    }
}
