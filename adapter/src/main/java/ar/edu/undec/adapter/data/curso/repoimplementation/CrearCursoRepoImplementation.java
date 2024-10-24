package ar.edu.undec.adapter.data.curso.repoimplementation;

import ar.edu.undec.adapter.data.curso.crud.CrearCursoCrud;
import ar.edu.undec.adapter.data.curso.mapper.CursoMapper;
import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import curso.modelo.Curso;
import curso.output.CrearCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CrearCursoRepoImplementation implements CrearCursoRepository {
    CrearCursoCrud crearCursoCrud;

    @Autowired
    public CrearCursoRepoImplementation(CrearCursoCrud crearCursoCrud) {
        this.crearCursoCrud = crearCursoCrud;
    }

    @Override
    public UUID crearCurso(Curso curso) {
        try{
            CursoEntidad cursoEntidad = CursoMapper.coreDataMapper(curso);
            cursoEntidad = crearCursoCrud.save(cursoEntidad);
            return cursoEntidad.getId();
        } catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public boolean buscarCurso(UUID id) {
        return crearCursoCrud.buscar(id).isPresent();
    }
}
