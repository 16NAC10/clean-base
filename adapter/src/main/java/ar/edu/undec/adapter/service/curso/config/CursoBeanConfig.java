package ar.edu.undec.adapter.service.curso.config;

import ar.edu.undec.adapter.data.curso.repoimplementation.BuscarCursoPorIdRepoImplementation;
import ar.edu.undec.adapter.data.curso.repoimplementation.CrearCursoRepoImplementation;
import curso.input.BuscarCursoPorIdInput;
import curso.input.CrearCursoInput;
import curso.usecase.BuscarCursoPorIdUseCase;
import curso.usecase.crearcursousecase.CrearCursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CursoBeanConfig {

    @Bean
    public CrearCursoInput crearCursoInput(CrearCursoRepoImplementation crearCursoRepoImplementation, BuscarCursoPorIdRepoImplementation buscarCursoPorIdRepoImplementation) {
        return new CrearCursoUseCase(crearCursoRepoImplementation, buscarCursoPorIdRepoImplementation);
    }

    @Bean
    public BuscarCursoPorIdInput buscarCursoPorIdInput(BuscarCursoPorIdRepoImplementation buscarCursoPorIdRepoImplementation) {
        return new BuscarCursoPorIdUseCase(buscarCursoPorIdRepoImplementation);
    }
}
