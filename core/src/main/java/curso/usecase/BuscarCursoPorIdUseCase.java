package curso.usecase;

import curso.exception.CursoNoExisteException;
import curso.input.BuscarCursoPorIdInput;
import curso.modelo.Curso;
import curso.output.BuscarCursoPorIdRepository;

import java.util.UUID;

public class BuscarCursoPorIdUseCase implements BuscarCursoPorIdInput {
    private BuscarCursoPorIdRepository buscarCursoPorIdRepository;

    public BuscarCursoPorIdUseCase(BuscarCursoPorIdRepository buscarCursoPorIdRepository){
        this.buscarCursoPorIdRepository = buscarCursoPorIdRepository;
    }

    @Override
    public Curso buscarCursoPorId(UUID id) {
        Curso curso = buscarCursoPorIdRepository.buscarCursoPorId(id);
        if(curso == null){
            throw new CursoNoExisteException("El curso no existe");
        }
        return curso;
    }
}
