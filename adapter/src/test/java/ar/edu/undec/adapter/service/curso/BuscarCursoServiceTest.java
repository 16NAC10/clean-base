package ar.edu.undec.adapter.service.curso;

import ar.edu.undec.adapter.service.curso.controller.BuscarCursoController;
import ar.edu.undec.adapter.service.curso.dto.CursoDto;
import curso.input.BuscarCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarCursoServiceTest {

    @Mock
    BuscarCursoInput buscarCursoInput;

    @InjectMocks
    BuscarCursoController buscarCursoController;

    UUID id = UUID.randomUUID();

    @Test
    public void buscarCursoPorId_cursoEncontrado_returnCurso() {
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoInput.buscarCursoPorId(id)).thenReturn(curso);
        ResponseEntity<?> response = buscarCursoController.buscarCursoPorId(id);
        CursoDto cursoResponse = (CursoDto) response.getBody();
        Assertions.assertEquals(curso.getId(), cursoResponse.getId());
        Assertions.assertEquals(curso.getNombre(), cursoResponse.getNombre());
        Assertions.assertEquals(curso.getFechaCierreInscripcion(), cursoResponse.getFechaCierreInscripcion());
        Assertions.assertEquals(curso.getNivel(), cursoResponse.getNivel());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void buscarCursoPorId_cursoNoEncontrado_Exception() {
        when(buscarCursoInput.buscarCursoPorId(id)).thenThrow(new RuntimeException("El curso no existe"));
        ResponseEntity<?> response = buscarCursoController.buscarCursoPorId(id);
        Assertions.assertEquals(response.getStatusCodeValue(), 404);
        Assertions.assertEquals(response.getBody(), "El curso no existe");
    }

    @Test
    public void buscarCursoPorNombre_cursoEncontrado_returnCurso() {
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoInput.buscarCursoPorNombre(curso.getNombre())).thenReturn(curso);
        ResponseEntity<?> response = buscarCursoController.buscarCursoPorNombre(curso.getNombre());
        CursoDto cursoResponse = (CursoDto) response.getBody();
        Assertions.assertEquals(curso.getId(), cursoResponse.getId());
        Assertions.assertEquals(curso.getNombre(), cursoResponse.getNombre());
        Assertions.assertEquals(curso.getNivel(), cursoResponse.getNivel());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void buscarCursoPorNombre_cursoNoEncontrado_Exception() {
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(buscarCursoInput.buscarCursoPorNombre(curso.getNombre())).thenThrow(new RuntimeException("El curso no existe"));
        ResponseEntity<?> response = buscarCursoController.buscarCursoPorNombre(curso.getNombre());
        Assertions.assertEquals(response.getStatusCodeValue(), 404);
        Assertions.assertEquals(response.getBody(), "El curso no existe");
    }

    @Test
    public void buscarCursos_cursoEncontrado_returnCursoList() {
        List<Curso> cursos = Arrays.asList(
                Curso.factory(UUID.randomUUID(), "Programación", LocalDate.MAX, CursoNivel.MEDIO),
                Curso.factory(UUID.randomUUID(), "Matemática", LocalDate.MAX, CursoNivel.INICIAL)
        );
        when(buscarCursoInput.buscarCursos()).thenReturn(cursos);
        ResponseEntity<?> response = buscarCursoController.buscarCursos();
        List<CursoDto> cursoResponse = (List<CursoDto>) response.getBody();
        Assertions.assertEquals(cursos.size(), cursoResponse.size());
        Assertions.assertEquals(cursos.get(0).getId(), cursoResponse.get(0).getId());
        Assertions.assertEquals(cursos.get(0).getNombre(), cursoResponse.get(0).getNombre());
        Assertions.assertEquals(cursos.get(0).getNivel(), cursoResponse.get(0).getNivel());
        Assertions.assertEquals(cursos.get(1).getId(), cursoResponse.get(1).getId());
        Assertions.assertEquals(cursos.get(1).getNombre(), cursoResponse.get(1).getNombre());
        Assertions.assertEquals(cursos.get(1).getNivel(), cursoResponse.get(1).getNivel());
        Assertions.assertEquals(response.getStatusCodeValue(), 200);
    }

    @Test
    public void buscarCursos_cursoNoEncontrado_Exception() {
        when(buscarCursoInput.buscarCursos()).thenThrow(new RuntimeException("No hay cursos registrados"));
        ResponseEntity<?> response = buscarCursoController.buscarCursos();
        Assertions.assertEquals(response.getStatusCodeValue(), 404);
        Assertions.assertEquals(response.getBody(), "No hay cursos registrados");
    }
}
