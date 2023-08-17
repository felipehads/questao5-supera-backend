package br.com.banco.domain.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferenciaRequest {

    private LocalDateTime dataTransferencia;
    private Double valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private Integer idConta;
}
