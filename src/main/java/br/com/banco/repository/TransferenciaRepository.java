package br.com.banco.repository;

import br.com.banco.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    public List<TransferenciaEntity> findAllByContaIdConta(Integer idConta);
    public List<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacao(Integer idConta, String nomeOperadorTransacao);
    public List<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaLessThan(Integer idConta, String nomeOperadorTransacao, LocalDateTime dataFim);
    public List<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaGreaterThan(Integer idConta, String nomeOperadorTransacao, LocalDateTime dataInicio);
    public List<TransferenciaEntity> findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaBetween(Integer idConta, String nomeOperadorTransacao, LocalDateTime dataInicio, LocalDateTime dataFim);
    public List<TransferenciaEntity> findAllByContaIdContaAndDataTransferenciaGreaterThan(Integer idConta, LocalDateTime dataInicio);
    public List<TransferenciaEntity> findAllByContaIdContaAndDataTransferenciaLessThan(Integer idConta, LocalDateTime dataFim);
    public List<TransferenciaEntity> findAllByContaIdContaAndDataTransferenciaBetween(Integer idConta, LocalDateTime dataInicio, LocalDateTime dataFim);

}
