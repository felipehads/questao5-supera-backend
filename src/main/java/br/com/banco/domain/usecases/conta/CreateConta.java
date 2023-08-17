package br.com.banco.domain.usecases.conta;

import br.com.banco.domain.dto.request.CreateContaRequest;
import br.com.banco.domain.dto.response.ContaResponse;
import br.com.banco.entity.ContaEntity;
import br.com.banco.service.ContaService;
import br.com.banco.service.MapperService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateConta {

    private final ContaService contaService;
    private final MapperService mapperService;

    public ContaResponse execute(CreateContaRequest request){
        ContaEntity conta = contaService.createConta(request);
        return mapperService.map(conta, ContaResponse.class);
    }
}
