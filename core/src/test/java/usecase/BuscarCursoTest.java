package usecase;

import curso.exception.CursoNoExisteException;
import curso.input.BuscarCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
import curso.output.BuscarCursoRepository;
import curso.usecase.BuscarCursoUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarCursoTest {

    BuscarCursoInput buscarCursoInput;

    @Mock
    BuscarCursoRepository buscarCursoRepository;

    @BeforeEach
    void setUp() {
        buscarCursoInput = new BuscarCursoUseCase(buscarCursoRepository);
    }

    UUID id = UUID.randomUUID();

    @Test
    void buscarCursoPorId_cursoExiste_returnCurso() {
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoRepository.buscarCursoPorId(id)).thenReturn(curso);
        Assertions.assertEquals(curso, buscarCursoInput.buscarCursoPorId(id));
    }

    @Test
    void buscarCursoPorId_cursoNoExiste_Exception(){
        when(buscarCursoRepository.buscarCursoPorId(id)).thenThrow(CursoNoExisteException.class);
        Assertions.assertThrows(CursoNoExisteException.class, () -> buscarCursoInput.buscarCursoPorId(id));
    }

    @Test
    void buscarCursoPorNome_cursoExiste_returnCurso() {
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoRepository.buscarCursoPorNombre(curso.getNombre())).thenReturn(curso);
        Assertions.assertEquals(curso, buscarCursoInput.buscarCursoPorNombre(curso.getNombre()));
    }

    @Test
    void buscarCursoPorNome_cursoNoExiste_Exception(){
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoRepository.buscarCursoPorNombre(curso.getNombre())).thenThrow(CursoNoExisteException.class);
        Assertions.assertThrows(CursoNoExisteException.class, () -> buscarCursoInput.buscarCursoPorNombre(curso.getNombre()));
    }

    @Test
    void buscarCursos_cursoExiste_returnCursoList() {
        List<Curso> cursos = Arrays.asList(
                Curso.factory(UUID.randomUUID(), "Programación", LocalDate.MAX, CursoNivel.MEDIO),
                Curso.factory(UUID.randomUUID(), "Matemática", LocalDate.MAX, CursoNivel.INICIAL)
        );
        when(buscarCursoRepository.buscarCursos()).thenReturn(cursos);
        Assertions.assertEquals(cursos, buscarCursoInput.buscarCursos());
    }

    @Test
    void buscarCursos_cursoNoExiste_Exception(){
        when(buscarCursoRepository.buscarCursos()).thenThrow(CursoNoExisteException.class);
        Assertions.assertThrows(CursoNoExisteException.class, () -> buscarCursoInput.buscarCursos());
    }
}
