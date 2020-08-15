package io.github.rhacs.faenas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.faenas.modelos.Faena;

@Repository
public interface FaenasRepositorio extends JpaRepository<Faena, Long> {

}
