package br.com.banco.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conta")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Integer idConta;

    @Column(name = "nome_responsavel", length = 50)
    private String nomeResponsavel;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferenciaEntity> transferencias;

}
