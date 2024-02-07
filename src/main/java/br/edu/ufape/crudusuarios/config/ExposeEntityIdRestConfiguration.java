package br.edu.ufape.crudusuarios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import br.edu.ufape.crudusuarios.model.Estudante;
import br.edu.ufape.crudusuarios.model.Professor;

@Configuration
public class ExposeEntityIdRestConfiguration implements RepositoryRestConfigurer {

	@Override
    public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Estudante.class);
        config.exposeIdsFor(Professor.class);
    }
}
