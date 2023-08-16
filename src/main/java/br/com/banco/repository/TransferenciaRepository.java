package br.com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entity.TransferenciaEntity;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer>, TransferenciaCustomRepository {
}
