package br.edu.ifpi.infovita.dto.enderecoEstabelecimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEstabelecimentoRequestBody {
    private String placeId;
    private String bairro;
    private String complemento;
    private String municipio;
    private String numero;
    private String logradouro;
}
