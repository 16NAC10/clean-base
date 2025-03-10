package ar.edu.undec.adapter.data.curso.repoimplementation;

import ar.edu.undec.adapter.data.curso.crud.BuscarCursoCrud;
import ar.edu.undec.adapter.data.curso.mapper.CursoMapper;
import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import curso.modelo.Curso;
import curso.output.BuscarCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuscarCursoRepoImplementation implements BuscarCursoRepository {
    BuscarCursoCrud buscarCursoCrud;

    @Autowired
    public BuscarCursoRepoImplementation(BuscarCursoCrud buscarCursoCrud) {
        this.buscarCursoCrud = buscarCursoCrud;
    }

    @Override
    public Curso buscarCursoPorId(UUID id) {
        CursoEntidad cursoEntidad = buscarCursoCrud.findById(id).orElse(null);
        if (cursoEntidad == null) {
            return null;
        } else {
            return CursoMapper.dataCoreMapper(cursoEntidad);
        }
    }

    @Override
    public Curso buscarCursoPorNombre(String nombre) {
        CursoEntidad cursoEntidad = buscarCursoCrud.findByNombre(nombre).orElse(null);
        if (cursoEntidad == null) {
            return null;
        } else {
            return CursoMapper.dataCoreMapper(cursoEntidad);
        }
    }

    @Override
    public List<Curso> buscarCursos() {
        List<CursoEntidad> cursosData = buscarCursoCrud.findAll();
        List<Curso> cursos = new ArrayList<>();
        for (CursoEntidad cursoEntidad : cursosData) {
            cursos.add(CursoMapper.dataCoreMapper(cursoEntidad));
        }
        return cursos;
    }
}
