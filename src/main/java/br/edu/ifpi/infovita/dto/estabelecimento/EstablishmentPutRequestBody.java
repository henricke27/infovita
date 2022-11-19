package br.edu.ifpi.infovita.dto.estabelecimento;

import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EstablishmentAddressRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentPutRequestBody {
    @NotNull(message = "ID cannot be null") @Positive(message = "Equipment ID cannot be a zero or negative")
    private Long id;
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    @NotBlank(message = "Business name cannot be null or empty")
    private String businessName;
    @NotBlank(message = "CNES cannot be null or empty")
    private String cnes;
    @NotBlank(message = "CNPJ cannot be null or empty")
    private String cnpj;
    private Boolean sus;
    private EstablishmentAddressRequestBody address;
}
