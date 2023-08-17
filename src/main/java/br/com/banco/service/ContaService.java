package br.com.banco.service;

import br.com.banco.domain.dto.request.CreateContaRequest;
import br.com.banco.domain.dto.request.UpdateContaRequest;
import br.com.banco.domain.dto.response.ContaResponse;
import br.com.banco.entity.ContaEntity;
import br.com.banco.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Page<ContaEntity> findAllConta(Integer page, Integer size){
        return contaRepository.findAll(PageRequest.of(page, size));
    }

    public ContaEntity updateContaNameById(Integer id, UpdateContaRequest request) {
        ContaEntity conta = contaRepository.getById(id);
        conta.setNomeResponsavel(request.getNomeResponsavel());
        return contaRepository.save(conta);
    }

    public ContaEntity createConta(CreateContaRequest request) {
        ContaEntity conta = ContaEntity.builder().nomeResponsavel(request.getNomeResponsavel()).build();
        return contaRepository.save(conta);
    }

    public ContaEntity findById(Integer id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public void deleteById(Integer id) {
        contaRepository.deleteById(id);
    }
}
