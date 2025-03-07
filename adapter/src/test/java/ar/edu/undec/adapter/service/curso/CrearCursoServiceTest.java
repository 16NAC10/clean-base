package ar.edu.undec.adapter.service.curso;

import ar.edu.undec.adapter.service.curso.controller.CrearCursoController;
import ar.edu.undec.adapter.service.curso.dto.CursoDto;
import curso.exception.CursoExisteException;
import curso.input.CrearCursoInput;
import curso.modelo.CursoNivel;
import curso.usecase.crearcursousecase.CrearCursoRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearCursoServiceTest {

    @Mock
    CrearCursoInput crearCursoInput;

    @InjectMocks
    CrearCursoController crearCursoController;



    @Test
    public void crearCurso_cursoCreado_ReturnHTTP201() {
        UUID id = UUID.randomUUID();
        when(crearCursoInput.crearCurso(any(CrearCursoRequestModel.class))).thenReturn(id);
        ResponseEntity<?> crearCursoResponse = crearCursoController.crearCurso(CursoDto.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO));
        Assertions.assertEquals(HttpStatus.CREATED, crearCursoResponse.getStatusCode());
        Assertions.assertEquals("El curso se ha registrado correctamente", crearCursoResponse.getBody());
    }

    @Test
    public void crearCurso_cursoExiste_ReturnHTTP409() {
        UUID id = UUID.randomUUID();
        doThrow(CursoExisteException.class).when(crearCursoInput).crearCurso(any(CrearCursoRequestModel.class));
        ResponseEntity<?> crearCursoResponse = crearCursoController.crearCurso(CursoDto.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO));
        Assertions.assertEquals(HttpStatus.CONFLICT, crearCursoResponse.getStatusCode());
    }

    @Test
    public void crearCurso_idNull_ReturnHTTP400() {
        UUID id = null;
        ResponseEntity<?> crearCursoResponse = crearCursoController.crearCurso(CursoDto.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, crearCursoResponse.getStatusCode());
        Assertions.assertNull(crearCursoResponse.getBody());
    }
}
