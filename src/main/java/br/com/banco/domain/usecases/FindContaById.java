package br.com.banco.domain.usecases;

import br.com.banco.domain.dto.response.ContaResponse;
import br.com.banco.entity.ContaEntity;
import br.com.banco.service.ContaService;
import br.com.banco.service.MapperService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindContaById {

    private final ContaService contaService;
    private final MapperService mapperService;

    public ContaResponse execute(Integer id) {
        ContaEntity conta = contaService.findById(id);
        return mapperService.map(conta, ContaResponse.class);
    }
}
