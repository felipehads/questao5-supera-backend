package br.com.banco.database.repository;

import java.util.List;

import br.com.banco.database.entity.TransferenciaEntity;
import br.com.banco.domain.filtro.FiltroTransferencia;

public interface TransferenciaCustomRepository {
    public List<TransferenciaEntity> findAllByFiltro(FiltroTransferencia filtro);
}
