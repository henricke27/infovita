package br.edu.ifpi.infovita.dto.estabelecimento;

import br.edu.ifpi.infovita.dto.equipamento.EquipamentoResponseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoResponseBody {
    private Long id;
    private String nome;
    private String nomeEmpresarial;
    private String cnes;
    private String cnpj;
    private EnderecoEstabelecimentoResponseBody endereco;
    private List<EquipamentoResponseBody> equipamentos = new ArrayList<>();
}
