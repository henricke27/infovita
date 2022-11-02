package br.edu.ifpi.infovita.dto.estabelecimentoEquipamento;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Estabelecimento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoEquipamentoResponseBody {
    private Long id;
    private String nome;
    private Integer existentes;
    private Integer funcionais;
}
