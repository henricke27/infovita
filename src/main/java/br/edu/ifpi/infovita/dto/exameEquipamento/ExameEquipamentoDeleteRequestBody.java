package br.edu.ifpi.infovita.dto.exameEquipamento;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameEquipamentoDeleteRequestBody {
    private Long exame;
    private Long equipamento;
}
