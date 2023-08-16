package br.com.banco.service;


import java.util.List;

import br.com.banco.domain.dto.response.TransferenciaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.domain.dto.request.GetTransferenciaRequest;
import br.com.banco.entity.TransferenciaEntity;
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

    public Page<TransferenciaEntity> findAllTransferenciaByFiltro(GetTransferenciaRequest request, Integer page, Integer size) {
        if (request.getOperadorTransacao() != null) {
            if (request.getDataInicio() != null && request.getDataFimLocalDateTime() != null) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaBetween(request.getIdConta(), request.getOperadorTransacao(), request.getDataInicioLocalDateTime(), request.getDataFimLocalDateTime(), PageRequest.of(page,size));
            }

            if (request.getDataInicioLocalDateTime() != null) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaGreaterThan(request.getIdConta(), request.getOperadorTransacao(), request.getDataInicioLocalDateTime(), PageRequest.of(page,size));
            } else if (request.getDataFimLocalDateTime() != null) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaLessThan(request.getIdConta(), request.getOperadorTransacao(), request.getDataFimLocalDateTime(), PageRequest.of(page,size));
            }

            if(request.getOperadorTransacao().isBlank()) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacao(request.getIdConta(), null, PageRequest.of(page,size));
            }

            return repository.findAllByContaIdContaAndNomeOperadorTransacao(request.getIdConta(), request.getOperadorTransacao(), PageRequest.of(page,size));
        }

        if (request.getDataInicioLocalDateTime() != null && request.getDataFimLocalDateTime() != null) {
            return repository.findAllByContaIdContaAndDataTransferenciaBetween(request.getIdConta(), request.getDataInicioLocalDateTime(), request.getDataFimLocalDateTime(), PageRequest.of(page,size));
        }

        if (request.getDataInicioLocalDateTime() != null) {
            return repository.findAllByContaIdContaAndDataTransferenciaGreaterThan(request.getIdConta(), request.getDataInicioLocalDateTime(), PageRequest.of(page,size));
        } else if (request.getDataFimLocalDateTime() != null) {
            return repository.findAllByContaIdContaAndDataTransferenciaLessThan(request.getIdConta(), request.getDataFimLocalDateTime(), PageRequest.of(page,size));
        }
        return repository.findAllByContaIdConta(request.getIdConta(), PageRequest.of(page,size));
    }
}
