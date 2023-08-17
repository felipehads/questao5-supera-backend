package br.com.banco.domain.usecases;

import br.com.banco.domain.dto.request.UpdateTransferenciaRequest;
import br.com.banco.domain.dto.response.TransferenciaResponse;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.service.MapperService;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateTransferenciaById {
    private final TransferenciaService transferenciaService;
    private final MapperService mapperService;

    public TransferenciaResponse execute(Integer id, UpdateTransferenciaRequest request) {
        TransferenciaEntity transferenciaEntity = transferenciaService.updateTransferenciaById(id, request);
        return mapperService.map(transferenciaEntity, TransferenciaResponse.class);
    }
}
