package br.edu.ifpi.infovita.dto.estabelecimentoEquipamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoEquipamentoPostRequest {
    private Long equipamento;
    private Long estabelecimento;
    private Integer existentes;
    private Integer funcionais;
    private Boolean sus;
}
