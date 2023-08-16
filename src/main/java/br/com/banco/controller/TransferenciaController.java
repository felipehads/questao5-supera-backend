package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.domain.dto.request.GetTransferenciaRequest;
import br.com.banco.domain.usecases.transferencia.FindAllTransferenciaByFiltro;
import br.com.banco.service.TransferenciaService;

@RequestMapping("transferencia")
@RestController
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<TransferenciaEntity>> getTransferenciasByFiltro(GetTransferenciaRequest request) {
        List<TransferenciaEntity> transferencias = new FindAllTransferenciaByFiltro(transferenciaService).execute(request);
        return ResponseEntity.ok(transferencias);
    }
    
}
