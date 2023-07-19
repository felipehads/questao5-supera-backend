package br.com.banco.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.database.entity.TransferenciaEntity;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer>, TransferenciaCustomRepository {
}
