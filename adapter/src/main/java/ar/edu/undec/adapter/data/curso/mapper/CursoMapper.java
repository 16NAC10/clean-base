package ar.edu.undec.adapter.data.curso.mapper;

import ar.edu.undec.adapter.data.curso.model.CursoEntidad;
import curso.modelo.Curso;

public class CursoMapper {
    public static Curso dataCoreMapper(CursoEntidad cursoEntidad){
        return Curso.factory(cursoEntidad.getId(), cursoEntidad.getNombre(), cursoEntidad.getFechaCierreInscripcion(),cursoEntidad.getNivel());
    }
    public static CursoEntidad coreDataMapper(Curso curso){
        return new CursoEntidad(curso.getId(), curso.getNombre(), curso.getFechaCierreInscripcion(), curso.getNivel());
    }
}
