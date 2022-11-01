package br.edu.ifpi.infovita.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nomeEmpresarial;
    private String cnes;
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEstabelecimento endereco;

    @OneToMany(mappedBy = "estabelecimento")
    private List<EstabelecimentoEquipamento> equipamentos = new ArrayList<>();
}
