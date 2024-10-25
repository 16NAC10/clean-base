package ar.edu.undec.adapter.data.curso.repoimplementation;

import ar.edu.undec.adapter.data.curso.crud.BuscarCursoPorIdCrud;
import ar.edu.undec.adapter.data.curso.mapper.CursoMapper;
import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import curso.modelo.Curso;
import curso.output.BuscarCursoPorIdRepository;
import java.util.UUID;

public class BuscarCursoPorIdRepoImplementation implements BuscarCursoPorIdRepository {
    BuscarCursoPorIdCrud buscarCursoPorId;

    public BuscarCursoPorIdRepoImplementation(BuscarCursoPorIdCrud buscarCursoPorId) {
        this.buscarCursoPorId = buscarCursoPorId;
    }

    @Override
    public Curso buscarCursoPorId(UUID id) {
        CursoEntidad cursoEntidad = buscarCursoPorId.buscarPorId(id).get();
        return CursoMapper.dataCoreMapper(cursoEntidad);
    }
}
