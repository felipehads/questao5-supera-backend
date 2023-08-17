package br.com.banco.controller;

import br.com.banco.domain.dto.request.CreateContaRequest;
import br.com.banco.domain.dto.request.UpdateContaRequest;
import br.com.banco.domain.dto.response.ContaResponse;
import br.com.banco.domain.usecases.DeleteContaById;
import br.com.banco.domain.usecases.FindContaById;
import br.com.banco.domain.usecases.conta.CreateConta;
import br.com.banco.domain.usecases.conta.FindAllConta;
import br.com.banco.domain.usecases.conta.UpdateContaNameById;
import br.com.banco.service.ContaService;
import br.com.banco.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conta")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;
    private final MapperService mapperService;

    @GetMapping()
    public ResponseEntity<Page<ContaResponse>> getAllContas(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "0") Integer page){
        Page<ContaResponse> contas = new FindAllConta(contaService, mapperService).execute(page, size);
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> getContaById(@PathVariable Integer id){
        ContaResponse conta = new FindContaById(contaService, mapperService).execute(id);
        return ResponseEntity.ok(conta);
    }

    @PostMapping()
    public ResponseEntity<ContaResponse> createConta(@RequestBody CreateContaRequest request){
        ContaResponse conta = new CreateConta(contaService, mapperService).execute(request);
        return ResponseEntity.ok(conta);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ContaResponse> updateContaNameById(@PathVariable Integer id, @RequestBody UpdateContaRequest request){
        ContaResponse conta = new UpdateContaNameById(contaService, mapperService).execute(id, request);
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContaById(@PathVariable Integer id){
        new DeleteContaById(contaService).execute(id);
        return ResponseEntity.status(204).build();
    }
}
