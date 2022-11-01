package br.edu.ifpi.infovita.dto.equipamento;

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
}
