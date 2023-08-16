package br.com.banco.controller;

import br.com.banco.domain.dto.request.GetTransferenciaRequest;
import br.com.banco.domain.dto.response.TransferenciaResponse;
import br.com.banco.domain.usecases.transferencia.FindAllTransferenciaByFiltro;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.service.MapperService;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("transferencia")
@RestController
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @Autowired
    private MapperService mapperService;

    @GetMapping("/filtrar")
    public ResponseEntity<Page<TransferenciaResponse>> getTransferenciasByFiltro(GetTransferenciaRequest request, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "0") Integer page) {
        Page<TransferenciaResponse> transferencias = new FindAllTransferenciaByFiltro(transferenciaService, mapperService).execute(request, page, size);
        return ResponseEntity.ok(transferencias);
    }
    
}
