package br.edu.ifpi.infovita.dto.estabelecimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoPutRequestBody {
    private Long id;
    private String nome;
    private String nomeEmpresarial;
    private String cnes;
    private String cnpj;
    private EnderecoEstabelecimentoResponseBody endereco;
}
