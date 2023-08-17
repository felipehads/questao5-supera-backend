package br.com.banco.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransferenciaRequest {

    private LocalDateTime dataTransferencia;
    private Double valor;
    private String tipo;
    private String nomeOperadorTransacao;
}
