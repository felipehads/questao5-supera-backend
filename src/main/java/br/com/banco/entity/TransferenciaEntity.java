package br.com.banco.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transferencia")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransferenciaEntity {
    
    @Id
    private Integer id;

    @Column(name = "data_transferencia")
    private LocalDateTime dataTransferencia;

    @Column(name = "valor")
    private Double valor;
    
    @Column(name = "tipo", length = 15)
    private String tipo;

    @Column(name = "nome_operador_transacao", length = 50)
    private String nomeOperadorTransacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private ContaEntity conta;

}
