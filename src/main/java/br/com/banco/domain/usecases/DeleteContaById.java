package br.com.banco.domain.usecases;

import br.com.banco.service.ContaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteContaById {
    private final ContaService contaService;

    public void execute(Integer id){
        contaService.deleteById(id);
    }
}
