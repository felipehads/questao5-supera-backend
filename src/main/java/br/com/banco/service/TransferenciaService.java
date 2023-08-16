package br.com.banco.service;


import java.util.List;

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

    public List<TransferenciaEntity> findAllTransferenciaByFiltro(GetTransferenciaRequest request) {
        if (request.getOperadorTransacao() != null) {
            if (request.getDataInicio() != null && request.getDataFimLocalDateTime() != null) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaBetween(request.getIdConta(), request.getOperadorTransacao(), request.getDataInicioLocalDateTime(), request.getDataFimLocalDateTime());
            }

            if (request.getDataInicioLocalDateTime() != null) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaGreaterThan(request.getIdConta(), request.getOperadorTransacao(), request.getDataInicioLocalDateTime());
            } else if (request.getDataFimLocalDateTime() != null) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacaoAndDataTransferenciaLessThan(request.getIdConta(), request.getOperadorTransacao(), request.getDataFimLocalDateTime());
            }

            if(request.getOperadorTransacao().isBlank()) {
                return repository.findAllByContaIdContaAndNomeOperadorTransacao(request.getIdConta(), null);
            }

            return repository.findAllByContaIdContaAndNomeOperadorTransacao(request.getIdConta(), request.getOperadorTransacao());
        }

        if (request.getDataInicioLocalDateTime() != null && request.getDataFimLocalDateTime() != null) {
            return repository.findAllByContaIdContaAndDataTransferenciaBetween(request.getIdConta(), request.getDataInicioLocalDateTime(), request.getDataFimLocalDateTime());
        }

        if (request.getDataInicioLocalDateTime() != null) {
            return repository.findAllByContaIdContaAndDataTransferenciaGreaterThan(request.getIdConta(), request.getDataInicioLocalDateTime());
        } else if (request.getDataFimLocalDateTime() != null) {
            return repository.findAllByContaIdContaAndDataTransferenciaLessThan(request.getIdConta(), request.getDataFimLocalDateTime());
        }
        return repository.findAllByContaIdConta(request.getIdConta());
    }
}
