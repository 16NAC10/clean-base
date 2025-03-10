package model;

import curso.exception.CursoIncompletoException;
import curso.modelo.Curso;
import curso.modelo.CursoNivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.UUID;

public class CursoUnitTest {

    @Test
    public void instanciar_atributosCorrectos_Instancia() {
        Curso curso = Curso.factory(UUID.randomUUID(), "Programación", LocalDate.MAX, CursoNivel.MEDIO);
        Assertions.assertNotNull(curso);
    }

    @Test
    public void instanciar_faltaNombre_CursoIncompletoExcepcion() {
        Exception exceptionVacio = Assertions.assertThrows(CursoIncompletoException.class, () -> Curso.factory(UUID.randomUUID(), "", LocalDate.MAX, CursoNivel.MEDIO));
        Exception exceptionNulo = Assertions.assertThrows(CursoIncompletoException.class, () -> Curso.factory(UUID.randomUUID(), null, LocalDate.MAX, CursoNivel.MEDIO));
        Assertions.assertEquals("El nombre del curso es incorrecto", exceptionVacio.getMessage());
        Assertions.assertEquals("El nombre del curso es incorrecto", exceptionNulo.getMessage());
    }

    @Test
    public void instaciar_faltaFechaDeCierre_CursoIncompletoExcepcion() {
        Exception exceptionNulo = Assertions.assertThrows(CursoIncompletoException.class, () -> Curso.factory(UUID.randomUUID(), "Programación", null, CursoNivel.MEDIO));
        Exception exceptionFechaVencida = Assertions.assertThrows(CursoIncompletoException.class, () -> Curso.factory(UUID.randomUUID(), "Programación", LocalDate.now().plusDays(-1), CursoNivel.MEDIO));
        Assertions.assertEquals("La fecha de cierre de inscripción del curso no es válida", exceptionNulo.getMessage());
        Assertions.assertEquals("La fecha de cierre de inscripción del curso no es válida", exceptionFechaVencida.getMessage());
    }

    @Test
    public void instanciar_faltaNivelCurso_CursoIncompletoExcepcion() {
        Exception exceptionNulo = Assertions.assertThrows(CursoIncompletoException.class, () -> Curso.factory(UUID.randomUUID(), "Programación", LocalDate.MAX, null));
        Assertions.assertEquals("El nivel del curso no puede ser nulo", exceptionNulo.getMessage());
    }
}
