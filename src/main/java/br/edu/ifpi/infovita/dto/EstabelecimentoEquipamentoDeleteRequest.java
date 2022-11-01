package br.edu.ifpi.infovita.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoEquipamentoDeleteRequest {
    private Long equipamento;
    private Long estabelecimento;
}
