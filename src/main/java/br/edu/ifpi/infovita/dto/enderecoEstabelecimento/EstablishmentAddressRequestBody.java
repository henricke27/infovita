package br.edu.ifpi.infovita.dto.enderecoEstabelecimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentAddressRequestBody {
    private String placeId;
    private String district;
    private String complement;
    private String city;
    private String number;
    private String publicPlace;
}
