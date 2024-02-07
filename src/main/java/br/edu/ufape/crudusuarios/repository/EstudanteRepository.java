package br.edu.ufape.crudusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.crudusuarios.model.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

}
