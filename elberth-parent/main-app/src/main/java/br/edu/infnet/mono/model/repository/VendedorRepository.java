package br.edu.infnet.mono.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.mono.model.domain.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByCpf(String cpf);
    Optional<Vendedor> findByMatricula(Integer matricula);
}