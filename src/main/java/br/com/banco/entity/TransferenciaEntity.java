package br.com.banco.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencia")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransferenciaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_transferencia")
    private LocalDateTime dataTransferencia;

    @Column(name = "valor")
    private Double valor;
    
    @Column(name = "tipo", length = 15)
    private String tipo;

    @Column(name = "nome_operador_transacao", length = 50)
    private String nomeOperadorTransacao;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private ContaEntity conta;

}
