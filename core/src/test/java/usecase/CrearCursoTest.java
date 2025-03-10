package usecase;

import curso.exception.CursoExisteException;
import curso.input.CrearCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
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

    @Mock
    CrearCursoRepository crearCursoRepository;

    @BeforeEach
    void setUp() {crearCursoInput = new CrearCursoUseCase(crearCursoRepository);}

    UUID id = UUID.randomUUID();

    @Test
    void crearCurso_CursoNoExiste_returnId() {
        CrearCursoRequestModel curso = CrearCursoRequestModel.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(crearCursoRepository.buscarCurso(curso.getNombre())).thenReturn(false);
        when(crearCursoRepository.crearCurso(any(Curso.class))).thenReturn(id);
        Assertions.assertEquals(id, crearCursoInput.crearCurso(curso));
    }

    @Test
    void crearCurso_CursoExiste_Exception(){
        CrearCursoRequestModel curso = CrearCursoRequestModel.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(crearCursoRepository.buscarCurso(curso.getNombre())).thenReturn(true);
        Assertions.assertThrows(CursoExisteException.class, () -> crearCursoInput.crearCurso(curso));
    }
}
