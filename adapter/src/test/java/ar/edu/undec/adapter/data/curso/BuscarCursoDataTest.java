package ar.edu.undec.adapter.data.curso;

import ar.edu.undec.adapter.data.curso.crud.BuscarCursoCrud;
import ar.edu.undec.adapter.data.curso.mapper.CursoMapper;
import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import ar.edu.undec.adapter.data.curso.repoimplementation.BuscarCursoRepoImplementation;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarCursoDataTest {

    @Mock
    BuscarCursoCrud buscarCursoCrud;

    @InjectMocks
    BuscarCursoRepoImplementation buscarCursoRepoImplementation;

    @Test
    public void buscarCursoPorId_cursoExiste_returnCurso() {
        UUID id = UUID.randomUUID();
        CursoEntidad curso = new CursoEntidad(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoCrud.findById(any(UUID.class))).thenReturn(Optional.of(curso));
        CursoEntidad cursoResultado = CursoMapper.coreDataMapper(buscarCursoRepoImplementation.buscarCursoPorId(id));
        Assertions.assertEquals(curso, cursoResultado);
    }

    @Test
    public void buscarCursoPorId_cursoNoExiste_returnNull() {
        UUID id = UUID.randomUUID();
        when(buscarCursoCrud.findById(any(UUID.class))).thenReturn(Optional.empty());
        Curso curso = buscarCursoRepoImplementation.buscarCursoPorId(id);
        Assertions.assertNull(curso);
    }

    @Test
    public void buscarCursoPorNombre_cursoExiste_returnCurso() {
        UUID id = UUID.randomUUID();
        CursoEntidad curso = new CursoEntidad(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoCrud.findByNombre(any(String.class))).thenReturn(Optional.of(curso));
        CursoEntidad cursoResultado = CursoMapper.coreDataMapper(buscarCursoRepoImplementation.buscarCursoPorNombre(curso.getNombre()));
        Assertions.assertEquals(curso, cursoResultado);
    }

    @Test
    public void buscarCursoPorNombre_cursoNoExiste_returnNull() {
        UUID id = UUID.randomUUID();
        CursoEntidad cursoEntidad = new CursoEntidad(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoCrud.findByNombre(any(String.class))).thenReturn(Optional.empty());
        Curso curso = buscarCursoRepoImplementation.buscarCursoPorNombre(cursoEntidad.getNombre());
        Assertions.assertNull(curso);
    }

    @Test
    public void buscarCursos_cursoExiste_returnCursoList() {
        CursoEntidad curso1 = new CursoEntidad(UUID.randomUUID(), "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        CursoEntidad curso2 = new CursoEntidad(UUID.randomUUID(), "Matemática", LocalDate.MAX, CursoNivel.INICIAL);
        when(buscarCursoCrud.findAll()).thenReturn(Arrays.asList(curso1, curso2));
        List<Curso> cursos = buscarCursoRepoImplementation.buscarCursos();
        Assertions.assertNotNull(cursos);
        Assertions.assertEquals(2, cursos.size());
        Assertions.assertEquals(curso1.getNombre(), cursos.get(0).getNombre());
        Assertions.assertEquals(curso2.getNombre(), cursos.get(1).getNombre());
    }

    @Test
    public void buscarCursos_cursoNoExiste_returnNull() {
        when(buscarCursoCrud.findAll()).thenReturn(Arrays.asList());
        List<Curso> cursos = buscarCursoRepoImplementation.buscarCursos();
        Assertions.assertTrue(cursos.isEmpty());
    }
}
