package br.com.banco.domain.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTransferenciaRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;
    private String operadorTransacao;
    private Integer idConta;


    public LocalDateTime getDataInicioLocalDateTime() {
        if(this.dataInicio != null) {
            return this.dataInicio.atStartOfDay();
        }
        return null;
    }

    public LocalDateTime getDataFimLocalDateTime() {
        if(this.dataFim != null) {
            return this.dataFim.atStartOfDay();
        }
        return null;
    }
}
