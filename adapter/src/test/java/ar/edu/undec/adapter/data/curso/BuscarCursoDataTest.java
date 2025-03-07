package ar.edu.undec.adapter.data.curso;

import ar.edu.undec.adapter.data.curso.crud.BuscarCursoPorIdCrud;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarCursoDataTest {

    @Mock
    BuscarCursoPorIdCrud buscarCursoPorIdCrud;

    @InjectMocks
    BuscarCursoRepoImplementation buscarCursoPorIdRepoImplementation;

    @Test
    public void buscarCurso_cursoExiste_returnCurso() {
        UUID id = UUID.randomUUID();
        CursoEntidad curso = new CursoEntidad(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoPorIdCrud.findById(any(UUID.class))).thenReturn(Optional.of(curso));
        Curso cursoResultado = buscarCursoPorIdRepoImplementation.buscarCursoPorId(id);
        CursoMapper.coreDataMapper(cursoResultado);
        Assertions.assertEquals(curso.getNombre(), cursoResultado.getNombre());
        Assertions.assertEquals(curso.getFechaCierreInscripcion(), cursoResultado.getFechaCierreInscripcion());
        Assertions.assertEquals(curso.getNivel(), cursoResultado.getNivel());
    }
}
