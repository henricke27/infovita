package br.edu.ifpi.infovita.dto.equipamento;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Exame;
import br.edu.ifpi.infovita.dto.exame.ExameResponseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoResponseBody {
    private Long id;
    private String nome;

    public static EquipamentoResponseBody convertEquipamentToResponseDto(Equipamento equipamento){
        return EquipamentoResponseBody.builder()
                .id(equipamento.getId())
                .nome(equipamento.getNome())
                .build();
    }
}
