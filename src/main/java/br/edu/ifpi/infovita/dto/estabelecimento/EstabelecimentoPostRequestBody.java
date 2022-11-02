package br.edu.ifpi.infovita.dto.estabelecimento;

import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EnderecoEstabelecimentoRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoPostRequestBody {
    private String nome;
    private String nomeEmpresarial;
    private String cnes;
    private String cnpj;
    private Boolean sus;
    private EnderecoEstabelecimentoRequestBody endereco;
}
