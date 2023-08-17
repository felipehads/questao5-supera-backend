package br.com.banco.repository;

import br.com.banco.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {

    Page<ContaEntity> findAll(Pageable pageable);
}
