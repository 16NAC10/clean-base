package usecase;

import curso.exception.CursoExisteException;
import curso.input.CrearCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
import curso.output.BuscarCursoPorIdRepository;
import curso.output.CrearCursoRepository;
import curso.usecase.crearcursousecase.CrearCursoRequestModel;
import curso.usecase.crearcursousecase.CrearCursoUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearCursoTest {
    CrearCursoInput crearCursoInput;
    private UUID cursoId = UUID.randomUUID();

    @Mock
    CrearCursoRepository crearCursoRepository;

    @Mock
    BuscarCursoPorIdRepository buscarCursoPorIdRepository;

    @BeforeEach
    void setUp() {crearCursoInput = new CrearCursoUseCase(crearCursoRepository, buscarCursoPorIdRepository);}

    @Test
    void crearCurso_CursoNoExiste_crearCurso() {
        CrearCursoRequestModel curso = CrearCursoRequestModel.factory(cursoId, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(crearCursoRepository.buscarCurso(cursoId)).thenReturn(false);
        when(crearCursoRepository.crearCurso(any(Curso.class))).thenReturn(1);
        Assertions.assertEquals(1, crearCursoInput.crearCurso(curso));
    }

    @Test
    void crearCurso_CursoExiste_Exception(){
        CrearCursoRequestModel curso = CrearCursoRequestModel.factory(cursoId, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(crearCursoRepository.buscarCurso(cursoId)).thenReturn(true);
        Assertions.assertThrows(CursoExisteException.class, () -> crearCursoInput.crearCurso(curso));
    }
}
