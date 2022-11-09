package br.edu.ifpi.infovita.dto.exame;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Exame;
import br.edu.ifpi.infovita.dto.equipamento.EquipamentoResponseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameResponseBody {
    private Long id;
    private String nome;
    private List<EquipamentoResponseBody> equipamentos = new ArrayList<>();

    public static ExameResponseBody convertExameToResponseDto(Exame exame){
        return ExameResponseBody.builder()
                .id(exame.getId())
                .nome(exame.getNome())
                .equipamentos(exame.getEquipamentos().stream()
                        .map(EquipamentoResponseBody::convertEquipamentToResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
