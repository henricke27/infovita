package br.edu.ifpi.infovita.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento {
    @Id
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "equipamento") @JsonIgnore
    private List<EstabelecimentoEquipamento> estabelecimentos = new ArrayList<>();
}
