package ar.edu.undec.adapter.data.curso.crud;

import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuscarCursoCrud extends CrudRepository<CursoEntidad, UUID> {
    Optional<CursoEntidad> findById(UUID id);
    Optional<CursoEntidad> findByNombre(String nombre);
    List<CursoEntidad> findAll();
}
