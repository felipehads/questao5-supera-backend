package br.com.banco.repository;

import java.util.List;

import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.domain.dto.request.GetTransferenciaRequest;

public interface TransferenciaCustomRepository {
    public List<TransferenciaEntity> findAllByFiltro(GetTransferenciaRequest filtro);
}
