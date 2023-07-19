package br.com.banco.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conta")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContaEntity {
    
    @Id
    @Column(name = "id_conta")
    private Integer idConta;

    @Column(name = "nome_responsavel", length = 50)
    private String nomeResponsavel;

}
