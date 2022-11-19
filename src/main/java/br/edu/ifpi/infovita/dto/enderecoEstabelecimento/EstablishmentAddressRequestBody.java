package br.edu.ifpi.infovita.dto.enderecoEstabelecimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentAddressRequestBody {
    @NotBlank(message = "Place ID cannot be null or empty")
    private String placeId;
    private String district;
    private String complement;
    private String city;
    private String number;
    private String publicPlace;
}
