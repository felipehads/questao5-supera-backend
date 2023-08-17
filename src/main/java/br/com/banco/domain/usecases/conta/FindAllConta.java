package br.com.banco.domain.usecases.conta;

import br.com.banco.domain.dto.response.ContaResponse;
import br.com.banco.entity.ContaEntity;
import br.com.banco.service.ContaService;
import br.com.banco.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class FindAllConta {
    private final ContaService contaService;
    private final MapperService mapperService;

    public Page<ContaResponse> execute(Integer page, Integer size) {
        Page<ContaEntity> entities = contaService.findAllConta(page, size);
        return mapperService.mapPage(entities, ContaResponse.class);
    }
}
