package ar.edu.undec.adapter.service.curso.controller;

import ar.edu.undec.adapter.service.curso.dto.CursoDto;
import curso.input.BuscarCursoInput;
import curso.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cursos")
public class BuscarCursoController {

    private BuscarCursoInput buscarCursoInput;

    @Autowired
    public BuscarCursoController(BuscarCursoInput buscarCursoInput) {
        this.buscarCursoInput = buscarCursoInput;
    }

    @GetMapping(path = "/id={id}")
    public ResponseEntity<?> buscarCursoPorId(@PathVariable(name = "id") UUID id){
        try {
            Curso curso = buscarCursoInput.buscarCursoPorId(id);
            CursoDto cursoDto = CursoDto.factory(curso.getId(), curso.getNombre(), curso.getFechaCierreInscripcion(), curso.getNivel());
            return ResponseEntity.ok(cursoDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/curso={nombre}")
    public ResponseEntity<?> buscarCursoPorNombre(@PathVariable(name = "nombre") String nombre){
        try {
            Curso curso = buscarCursoInput.buscarCursoPorNombre(nombre);
            CursoDto cursoDto = CursoDto.factory(curso.getId(), curso.getNombre(), curso.getFechaCierreInscripcion(), curso.getNivel());
            return ResponseEntity.ok(cursoDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/lista")
    public ResponseEntity<?> buscarCursos(){
        try {
            List<Curso> cursos = buscarCursoInput.buscarCursos();
            List<CursoDto> cursoDtoList = new ArrayList<>();
            for (Curso curso : cursos) {
                CursoDto cursoDto = CursoDto.factory(curso.getId(), curso.getNombre(), curso.getFechaCierreInscripcion(), curso.getNivel());
                cursoDtoList.add(cursoDto);
            }
            return ResponseEntity.ok(cursoDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

