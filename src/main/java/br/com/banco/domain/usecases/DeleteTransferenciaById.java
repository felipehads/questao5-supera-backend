package br.com.banco.domain.usecases;

import br.com.banco.domain.dto.response.TransferenciaResponse;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.service.MapperService;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTransferenciaById {
    private final TransferenciaService transferenciaService;
    private final MapperService mapperService;

    public void execute(Integer id) {
        transferenciaService.deleteById(id);
    }
}
