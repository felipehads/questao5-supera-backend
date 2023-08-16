package br.com.banco.domain.usecases.transferencia;

import java.util.List;

import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.domain.dto.request.GetTransferenciaRequest;
import br.com.banco.service.TransferenciaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllTransferenciaByFiltro {
    
    private TransferenciaService transferenciaService;

    public List<TransferenciaEntity> execute(GetTransferenciaRequest request) {
        return transferenciaService.findAllTransferenciaByFiltro(request);
    }
    
}
