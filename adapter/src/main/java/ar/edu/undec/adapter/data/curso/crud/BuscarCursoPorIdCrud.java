package ar.edu.undec.adapter.data.curso.crud;

import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface BuscarCursoPorIdCrud extends CrudRepository<CursoEntidad, UUID> {
    public Optional<CursoEntidad> buscarPorId(UUID id);
}
