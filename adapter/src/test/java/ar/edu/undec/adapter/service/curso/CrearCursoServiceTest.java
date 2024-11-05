package ar.edu.undec.adapter.service.curso;

import ar.edu.undec.adapter.service.curso.controller.CrearCursoController;
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

    UUID id = UUID.randomUUID();

    @Test
    public void crearCurso_cursoCreado_ReturnHTTP201() {
        when(crearCursoInput.crearCurso(any(CrearCursoRequestModel.class))).thenReturn(id);
        ResponseEntity<?> crearCursoResponse = crearCursoController.crearCurso(CrearCursoRequestModel.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO));
        Assertions.assertEquals(HttpStatus.CREATED, crearCursoResponse.getStatusCode());
        Assertions.assertEquals(id, crearCursoResponse.getBody());
    }

    @Test
    public void crearCurso_cursoExiste_ReturnHTTP400() {
        doThrow(CursoExisteException.class).when(crearCursoInput).crearCurso(any(CrearCursoRequestModel.class));
        ResponseEntity<?> crearCursoResponse = crearCursoController.crearCurso(CrearCursoRequestModel.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, crearCursoResponse.getStatusCode());
        Assertions.assertEquals("El curso ya existe", crearCursoResponse.getBody());
    }
}
