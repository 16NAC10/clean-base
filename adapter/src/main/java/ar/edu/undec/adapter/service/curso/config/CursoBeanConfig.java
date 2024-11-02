package ar.edu.undec.adapter.service.curso.config;

import curso.input.BuscarCursoPorIdInput;
import curso.input.CrearCursoInput;
import curso.output.BuscarCursoPorIdRepository;
import curso.output.CrearCursoRepository;
import curso.usecase.BuscarCursoPorIdUseCase;
import curso.usecase.crearcursousecase.CrearCursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CursoBeanConfig {

    @Bean
    public CrearCursoInput crearCursoInput(CrearCursoRepository crearCursoRepository, BuscarCursoPorIdRepository buscarCursoPorIdRepository) {
        return new CrearCursoUseCase(crearCursoRepository, buscarCursoPorIdRepository);
    }

    @Bean
    public BuscarCursoPorIdInput buscarCursoPorIdInput(BuscarCursoPorIdRepository buscarCursoPorIdRepository) {
        return new BuscarCursoPorIdUseCase(buscarCursoPorIdRepository);
    }
}
