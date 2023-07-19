package br.com.banco.domain.usecases.transferencia;

import java.util.List;

import br.com.banco.database.entity.TransferenciaEntity;
import br.com.banco.domain.filtro.FiltroTransferencia;
import br.com.banco.service.TransferenciaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllTransferenciaByFiltro {
    
    private TransferenciaService transferenciaService;

    public List<TransferenciaEntity> execute(FiltroTransferencia filtro) {
        return transferenciaService.findAllTransferenciaByFiltro(filtro);
    }
    
}
