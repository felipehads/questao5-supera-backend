package br.com.banco.service;


import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banco.database.repository.TransferenciaRepository;
import br.com.banco.domain.filtro.FiltroTransferencia;
import br.com.banco.database.entity.TransferenciaEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferenciaService {
    
    private final TransferenciaRepository repository;

    // public List<TransferenciaEntity> findAll() {
    //     return repository.findAll();
    // }

    // public List<TransferenciaEntity> findAllTransferenciaByContaBancaria(Integer idConta) {
    //     return repository.findAllByContaIdConta(idConta);
    // }

    public List<TransferenciaEntity> findAllTransferenciaByFiltro(FiltroTransferencia filtro) {
        return repository.findAllByFiltro(filtro);
    }
    
}