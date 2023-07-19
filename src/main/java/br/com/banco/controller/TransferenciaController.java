package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.database.entity.TransferenciaEntity;
import br.com.banco.domain.filtro.FiltroTransferencia;
import br.com.banco.domain.usecases.transferencia.FindAllTransferenciaByFiltro;
import br.com.banco.service.TransferenciaService;

@RequestMapping("transferencia")
@RestController
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<TransferenciaEntity>> getTransferenciasByFiltro(FiltroTransferencia filtro) {
        List<TransferenciaEntity> transferencias = new FindAllTransferenciaByFiltro(transferenciaService).execute(filtro);
        return ResponseEntity.ok(transferencias);
    }
    
}
