package br.com.banco.domain.usecases;

import br.com.banco.domain.dto.response.TransferenciaResponse;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.service.MapperService;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class FindAllTransferencia {
    private final TransferenciaService transferenciaService;
    private final MapperService mapperService;

    public Page<TransferenciaResponse> execute(Integer page, Integer size) {
        Page<TransferenciaEntity> entities = transferenciaService.findAllTransferencia(page, size);
        return mapperService.mapPage(entities, TransferenciaResponse.class);
    }
}
