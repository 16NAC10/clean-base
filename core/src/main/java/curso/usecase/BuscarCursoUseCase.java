package curso.usecase;

import curso.exception.CursoNoExisteException;
import curso.input.BuscarCursoInput;
import curso.modelo.Curso;
import curso.output.BuscarCursoRepository;

import java.util.List;
import java.util.UUID;

public class BuscarCursoUseCase implements BuscarCursoInput {
    private BuscarCursoRepository buscarCursoRepository;

    public BuscarCursoUseCase(BuscarCursoRepository buscarCursoRepository){
        this.buscarCursoRepository = buscarCursoRepository;
    }

    @Override
    public Curso buscarCursoPorId(UUID id) {
        Curso curso = buscarCursoRepository.buscarCursoPorId(id);
        if(curso == null){
            throw new CursoNoExisteException("El curso no existe");
        }
        return curso;
    }

    @Override
    public Curso buscarCursoPorNombre(String nombre) {
        Curso curso = buscarCursoRepository.buscarCursoPorNombre(nombre);
        if(curso == null){
            throw new CursoNoExisteException("El curso no existe");
        }
        return curso;
    }

    @Override
    public List<Curso> buscarCursos() {
        List<Curso> cursos = buscarCursoRepository.buscarCursos();
        if(cursos.isEmpty()){
            throw new CursoNoExisteException("No hay cursos registrados");
        }
        return cursos;
    }
}
