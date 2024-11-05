package ar.edu.undec.adapter.service.curso.controller;

import curso.input.CrearCursoInput;
import curso.usecase.crearcursousecase.CrearCursoRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> crearCurso(@RequestBody CrearCursoRequestModel crearCursoRequestModel) {
        try{
            UUID id = crearCursoInput.crearCurso(crearCursoRequestModel);
            if(id != null){
                return ResponseEntity.created(null).body(id);
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("El curso ya existe");
        }
    }
}