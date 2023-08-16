package br.com.banco.repository;

import br.com.banco.entity.TransferenciaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    public Page<TransferenciaEntity> findAllByContaIdConta(Integer idConta, Pageable pageable);
    public Page<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacao(Integer idConta, String nomeOperadorTransacao, Pageable pageable);
    public Page<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaLessThan(Integer idConta, String nomeOperadorTransacao, LocalDateTime dataFim, Pageable pageable);
    public Page<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaGreaterThan(Integer idConta, String nomeOperadorTransacao, LocalDateTime dataInicio, Pageable pageable);
    public Page<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaBetween(Integer idConta, String nomeOperadorTransacao, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    public Page<TransferenciaEntity> findAllByContaIdContaAndDataTransferenciaGreaterThan(Integer idConta, LocalDateTime dataInicio, Pageable pageable);
    public Page<TransferenciaEntity> findAllByContaIdContaAndDataTransferenciaLessThan(Integer idConta, LocalDateTime dataFim, Pageable pageable);
    public Page<TransferenciaEntity> findAllByContaIdContaAndDataTransferenciaBetween(Integer idConta, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);

}
