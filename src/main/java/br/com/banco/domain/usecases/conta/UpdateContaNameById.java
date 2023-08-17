package br.com.banco.domain.usecases.conta;

import br.com.banco.domain.dto.request.UpdateContaRequest;
import br.com.banco.domain.dto.response.ContaResponse;
import br.com.banco.entity.ContaEntity;
import br.com.banco.service.ContaService;
import br.com.banco.service.MapperService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateContaNameById {

    private final ContaService contaService;
    private final MapperService mapperService;

    public ContaResponse execute(Integer id, UpdateContaRequest request) {
        ContaEntity contaEntity = contaService.updateContaNameById(id, request);
        return mapperService.map(contaEntity, ContaResponse.class);
    }
}
