package ar.edu.undec.adapter.service.curso.controller;

import ar.edu.undec.adapter.service.curso.dto.CursoDto;
import curso.input.BuscarCursoPorIdInput;
import curso.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("cursos")
public class BuscarCursoPorIdController {
    BuscarCursoPorIdInput buscarCursoPorIdInput;

    @Autowired
    public BuscarCursoPorIdController (BuscarCursoPorIdInput buscarCursoPorIdInput){
        this.buscarCursoPorIdInput = buscarCursoPorIdInput;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscarCursoPorId(@PathVariable(name = "id") UUID id){
        try {
            Curso curso = buscarCursoPorIdInput.buscarCursoPorId(id);
            CursoDto cursoDto = CursoDto.factory(curso.getId(), curso.getNombre(), curso.getFechaCierreInscripcion(), curso.getNivel());
            return ResponseEntity.ok(cursoDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
