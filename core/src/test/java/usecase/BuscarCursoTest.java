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
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarCursoTest {
    BuscarCursoInput buscarCursoInput;
    private UUID cursoId = UUID.randomUUID();

    @Mock
    BuscarCursoRepository buscarCursoRepository;

    @BeforeEach
    void setUp() {
        buscarCursoInput = new BuscarCursoUseCase(buscarCursoRepository);
    }

    @Test
    void buscarCurso_CursoExiste_RetornarCurso(){
        Curso curso = Curso.factory(cursoId, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoRepository.buscarCursoPorId(cursoId)).thenReturn(curso);
        Assertions.assertEquals(curso, buscarCursoInput.buscarCursoPorId(cursoId));
    }

    @Test
    void buscarCurso_CursoNoExiste_Exception(){
        when(buscarCursoRepository.buscarCursoPorId(cursoId)).thenThrow(CursoNoExisteException.class);
        Assertions.assertThrows(CursoNoExisteException.class, () -> buscarCursoInput.buscarCursoPorId(cursoId));
    }
}
