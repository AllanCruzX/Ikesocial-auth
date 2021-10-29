package com.ikesocial.auth.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query(" SELECT p FROM Pessoa p INNER JOIN FETCH p.contatos pc WHERE pc.descricao = :email ")
	Optional<Pessoa> buscarPessoaPorEmail(String email);
	
}
