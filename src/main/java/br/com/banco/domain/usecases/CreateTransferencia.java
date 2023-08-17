package br.com.banco.domain.usecases;

import br.com.banco.domain.dto.request.CreateTransferenciaRequest;
import br.com.banco.domain.dto.response.TransferenciaResponse;
import br.com.banco.entity.ContaEntity;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.service.ContaService;
import br.com.banco.service.MapperService;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTransferencia {

    private final TransferenciaService transferenciaService;
    private final ContaService contaService;
    private final MapperService mapperService;

    public TransferenciaResponse execute(CreateTransferenciaRequest request) {
        ContaEntity conta = contaService.findById(request.getIdConta());
        TransferenciaEntity transferencia = transferenciaService.insertTransferencia(request, conta);
        return mapperService.map(transferencia, TransferenciaResponse.class);
    }
}
