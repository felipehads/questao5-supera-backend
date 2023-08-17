package br.com.banco.controller;

import br.com.banco.domain.dto.request.CreateTransferenciaRequest;
import br.com.banco.domain.dto.request.FindTransferenciasRequest;
import br.com.banco.domain.dto.request.UpdateTransferenciaRequest;
import br.com.banco.domain.dto.response.TransferenciaResponse;
import br.com.banco.domain.usecases.*;
import br.com.banco.domain.usecases.transferencia.FindAllTransferenciaByFiltro;
import br.com.banco.service.ContaService;
import br.com.banco.service.MapperService;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("transferencia")
@RestController
@RequiredArgsConstructor
public class TransferenciaController {
    private final TransferenciaService transferenciaService;
    private final MapperService mapperService;
    private final ContaService contaService;

    @GetMapping("/filtrar")
    public ResponseEntity<Page<TransferenciaResponse>> getTransferenciasByFiltro(FindTransferenciasRequest request, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "0") Integer page) {
        Page<TransferenciaResponse> transferencias = new FindAllTransferenciaByFiltro(transferenciaService, mapperService).execute(request, page, size);
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping()
    public ResponseEntity<Page<TransferenciaResponse>> getAllTransferencias(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "0") Integer page){
        Page<TransferenciaResponse> transferencias = new FindAllTransferencia(transferenciaService, mapperService).execute(page, size);
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransferenciaResponse> getTransferenciaById(@PathVariable Integer id){
        TransferenciaResponse transferencia = new FindTransferenciaById(transferenciaService, mapperService).execute(id);
        return ResponseEntity.ok(transferencia);
    }
    @PostMapping("")
    public ResponseEntity<TransferenciaResponse> createTransferencia(@RequestBody CreateTransferenciaRequest request) {
        TransferenciaResponse transferencia = new CreateTransferencia(transferenciaService, contaService, mapperService).execute(request);
        return ResponseEntity.ok(transferencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferenciaResponse> updateTransferenciaById(@PathVariable Integer id, @RequestBody UpdateTransferenciaRequest request){
        TransferenciaResponse transferencia = new UpdateTransferenciaById(transferenciaService, mapperService).execute(id, request);
        return ResponseEntity.ok(transferencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransferenciaById(@PathVariable Integer id){
        new DeleteTransferenciaById(transferenciaService, mapperService).execute(id);
        return ResponseEntity.status(204).build();
    }



}
