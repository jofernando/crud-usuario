package br.edu.ufape.crudusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.edu.ufape.crudusuarios.model.Professor;

@Repository
@RepositoryRestResource(path = "professores")
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
