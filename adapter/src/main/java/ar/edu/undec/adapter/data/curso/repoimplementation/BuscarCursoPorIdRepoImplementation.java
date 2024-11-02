package ar.edu.undec.adapter.data.curso.repoimplementation;

import ar.edu.undec.adapter.data.curso.crud.BuscarCursoPorIdCrud;
import ar.edu.undec.adapter.data.curso.mapper.CursoMapper;
import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import curso.modelo.Curso;
import curso.output.BuscarCursoPorIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarCursoPorIdRepoImplementation implements BuscarCursoPorIdRepository {
    BuscarCursoPorIdCrud buscarCursoPorIdCrud;

    @Autowired
    public BuscarCursoPorIdRepoImplementation(BuscarCursoPorIdCrud buscarCursoPorIdCrud) {
        this.buscarCursoPorIdCrud = buscarCursoPorIdCrud;
    }

    @Override
    public Curso buscarCursoPorId(UUID id) {
        CursoEntidad cursoEntidad = buscarCursoPorIdCrud.buscarPorId(id).get();
        return CursoMapper.dataCoreMapper(cursoEntidad);
    }
}
