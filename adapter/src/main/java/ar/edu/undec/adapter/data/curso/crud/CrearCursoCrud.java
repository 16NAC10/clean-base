package ar.edu.undec.adapter.data.curso.crud;

import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CrearCursoCrud extends CrudRepository<CursoEntidad, Integer> {
    public Optional<CursoEntidad> buscar(UUID id);
}