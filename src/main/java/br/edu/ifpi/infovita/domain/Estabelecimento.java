package br.edu.ifpi.infovita.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nome_empresarial;
    private String cnes;
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEstabelecimento endereco;
}
