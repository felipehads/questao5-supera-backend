package br.com.banco.service;


import br.com.banco.domain.dto.request.CreateTransferenciaRequest;
import br.com.banco.domain.dto.request.UpdateTransferenciaRequest;
import br.com.banco.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.domain.dto.request.FindTransferenciasRequest;
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

    public Page<TransferenciaEntity> findAllTransferenciaByFiltro(FindTransferenciasRequest request, Integer page, Integer size) {
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

    public Page<TransferenciaEntity> findAllTransferencia(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public TransferenciaEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Transferencia não encontrada"));
    }

    public TransferenciaEntity updateTransferenciaById(Integer id, UpdateTransferenciaRequest request) {
        TransferenciaEntity entity = repository.getById(id);

        if (request.getDataTransferencia() != null) entity.setDataTransferencia(request.getDataTransferencia());
        if (request.getNomeOperadorTransacao() != null) entity.setNomeOperadorTransacao(request.getNomeOperadorTransacao());
        if (request.getValor() != null) entity.setValor(request.getValor());
        if (request.getTipo() != null) entity.setTipo(request.getTipo());

        return repository.save(entity);
    }

    public TransferenciaEntity insertTransferencia(CreateTransferenciaRequest request, ContaEntity conta) {
        TransferenciaEntity transferenciaEntity = TransferenciaEntity
                .builder()
                .dataTransferencia(request.getDataTransferencia())
                .valor(request.getValor())
                .tipo(request.getTipo())
                .nomeOperadorTransacao(request.getNomeOperadorTransacao())
                .conta(conta)
                .build();
        return repository.save(transferenciaEntity);

    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
