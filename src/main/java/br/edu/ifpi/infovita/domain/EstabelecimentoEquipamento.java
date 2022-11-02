package br.edu.ifpi.infovita.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoEquipamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JsonIgnore
    private Estabelecimento estabelecimento;
    @ManyToOne @JsonIgnore
    private Equipamento equipamento;
    private Integer existentes;
    private Integer funcionais;
}
