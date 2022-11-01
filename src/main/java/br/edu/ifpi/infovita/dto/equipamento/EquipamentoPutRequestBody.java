package br.edu.ifpi.infovita.dto.equipamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoPutRequestBody {
    private Long id;
    private String nome;
}
