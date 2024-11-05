package ar.edu.undec.adapter.data.curso;

import ar.edu.undec.adapter.data.curso.crud.CrearCursoCrud;
import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import ar.edu.undec.adapter.data.curso.repoimplementation.CrearCursoRepoImplementation;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearCursoDataTest {

    @Mock
    CrearCursoCrud crearCursoCrud;

    @InjectMocks
    CrearCursoRepoImplementation crearCursoRepoImplementation;

    UUID id = UUID.randomUUID();

    @Test
    public void crearCurso_cursoCreado_returnId() {
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        when(crearCursoCrud.save(any(CursoEntidad.class))).thenReturn(new CursoEntidad(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO));
        UUID resultado = crearCursoRepoImplementation.crearCurso(curso);
        Assertions.assertEquals(id, resultado);
    }

    @Test
    public void crearCurso_cursoNoCreado_returnNull() {
        Curso curso = Curso.factory(id, "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        doThrow(RuntimeException.class).when(crearCursoCrud).save(any(CursoEntidad.class));
        UUID resultado = crearCursoRepoImplementation.crearCurso(curso);
        Assertions.assertNull(resultado);
    }
}
