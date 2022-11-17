package br.edu.ifpi.infovita.dto.estabelecimento;

import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EstablishmentAddressRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentPostRequestBody {
    private String name;
    private String businessName;
    private String cnes;
    private String cnpj;
    private Boolean sus;
    private EstablishmentAddressRequestBody address;
}
