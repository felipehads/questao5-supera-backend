package br.com.banco.database.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.banco.database.entity.TransferenciaEntity;
import br.com.banco.domain.filtro.FiltroTransferencia;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransferenciaCustomRepositoryImpl implements TransferenciaCustomRepository {
    
    private final EntityManager entityManager;

    @Override
    public List<TransferenciaEntity> findAllByFiltro(FiltroTransferencia filtro) {

        String sql = "SELECT t FROM TransferenciaEntity t WHERE 1=1 ";
        Map<String, Object> paramMap = new HashMap<>();

        // Parâmetro obrigatório
        sql += "AND t.conta.idConta = :idConta ";
        paramMap.put("idConta", filtro.getIdConta());

        if(filtro.getOperadorTransacao() != null) {
            sql += "AND t.nomeOperadorTransacao = :operadorTransacao ";
            paramMap.put("operadorTransacao", filtro.getOperadorTransacao());
        }
        
        if(filtro.getDataInicio() != null) {
            sql += "AND cast(t.dataTransferencia as date) >= cast(:dataInicio as date) ";
            paramMap.put("dataInicio", filtro.getDataInicio());
        }

        if(filtro.getDataFim() != null) {
            sql += "AND cast(t.dataTransferencia as date) <= cast(:dataFim as date) ";
            paramMap.put("dataFim", filtro.getDataFim());
        }

        TypedQuery<TransferenciaEntity> query = entityManager.createQuery(sql, TransferenciaEntity.class);
        paramMap.forEach(query::setParameter);

        return query.getResultList();
    }
}
