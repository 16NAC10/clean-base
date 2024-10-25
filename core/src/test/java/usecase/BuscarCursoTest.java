package usecase;

import curso.exception.CursoNoExisteException;
import curso.input.BuscarCursoPorIdInput;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
import curso.output.BuscarCursoPorIdRepository;
import curso.usecase.BuscarCursoPorIdUseCase;
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
    BuscarCursoPorIdInput buscarCursoPorIdInput;
    private UUID cursoId = UUID.randomUUID();

    @Mock
    BuscarCursoPorIdRepository buscarCursoPorIdRepository;

    @BeforeEach
    void setUp() {
        buscarCursoPorIdInput = new BuscarCursoPorIdUseCase(buscarCursoPorIdRepository);
    }

    @Test
    void buscarCurso_CursoExiste_RetornarCurso(){
        Curso curso = Curso.factory(cursoId, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoPorIdRepository.buscarCursoPorId(cursoId)).thenReturn(curso);
        Assertions.assertEquals(curso, buscarCursoPorIdInput.buscarCursoPorId(cursoId));
    }

    @Test
    void buscarCurso_CursoNoExiste_Exception(){
        when(buscarCursoPorIdRepository.buscarCursoPorId(cursoId)).thenThrow(CursoNoExisteException.class);
        Assertions.assertThrows(CursoNoExisteException.class, () -> buscarCursoPorIdInput.buscarCursoPorId(cursoId));
    }
}
