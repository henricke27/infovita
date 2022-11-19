package br.edu.ifpi.infovita.dto.estabelecimento;

import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EstablishmentAddressRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentPostRequestBody {
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    @NotBlank(message = "Business name cannot be null or empty")
    private String businessName;
    @NotBlank(message = "CNES cannot be null or empty")
    private String cnes;
    @NotBlank(message = "CNPJ cannot be null or empty")
    private String cnpj;
    private Boolean sus;
    @NotNull(message = "Address cannot be null")
    private EstablishmentAddressRequestBody address;
}
