package ar.edu.undec.adapter.service.curso.config;

import curso.input.BuscarCursoInput;
import curso.input.CrearCursoInput;
import curso.output.BuscarCursoRepository;
import curso.output.CrearCursoRepository;
import curso.usecase.BuscarCursoUseCase;
import curso.usecase.crearcursousecase.CrearCursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CursoBeanConfig {

    @Bean
    public CrearCursoInput crearCursoInput(CrearCursoRepository crearCursoRepository) {
        return new CrearCursoUseCase(crearCursoRepository);
    }

    @Bean
    public BuscarCursoInput buscarCursoPorIdInput(BuscarCursoRepository buscarCursoRepository) {
        return new BuscarCursoUseCase(buscarCursoRepository);
    }
}
