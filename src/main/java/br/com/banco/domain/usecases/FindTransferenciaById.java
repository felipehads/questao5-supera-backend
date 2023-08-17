package br.com.banco.domain.usecases;

import br.com.banco.domain.dto.response.TransferenciaResponse;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.service.MapperService;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindTransferenciaById {
    private final TransferenciaService transferenciaService;
    private final MapperService mapperService;

    public TransferenciaResponse execute(Integer id) {
        TransferenciaEntity transferenciaEntity = transferenciaService.findById(id);
        return mapperService.map(transferenciaEntity, TransferenciaResponse.class);
    }
}
