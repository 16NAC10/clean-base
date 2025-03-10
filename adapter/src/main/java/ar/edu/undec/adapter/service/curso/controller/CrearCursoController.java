package ar.edu.undec.adapter.service.curso.controller;

import ar.edu.undec.adapter.service.curso.dto.CursoDto;
import curso.input.CrearCursoInput;
import curso.usecase.crearcursousecase.CrearCursoRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("cursos")
public class CrearCursoController {

    private CrearCursoInput crearCursoInput;

    @Autowired
    public CrearCursoController(CrearCursoInput crearCursoInput) {
        this.crearCursoInput = crearCursoInput;
    }

    @PostMapping
    public ResponseEntity<?> crearCurso(@RequestBody CursoDto cursoDto) {
        try{
            CrearCursoRequestModel curso = CrearCursoRequestModel.factory(cursoDto.getId(), cursoDto.getNombre(), cursoDto.getFechaCierreInscripcion(), cursoDto.getNivel());
            UUID id = crearCursoInput.crearCurso(curso);
            if(id != null){
                return ResponseEntity.created(null).body("El curso se ha registrado correctamente");
            }else{
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}