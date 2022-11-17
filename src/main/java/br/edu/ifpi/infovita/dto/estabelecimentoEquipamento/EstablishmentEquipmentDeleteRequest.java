package br.edu.ifpi.infovita.dto.estabelecimentoEquipamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentEquipmentDeleteRequest {
    @NotNull(message = "Equipment cannot be null") @Positive(message = "Equipment ID cannot be a zero or negative")
    private Long equipmentId;
    @NotNull(message = "Establishment ID cannot be null") @Positive(message = "Establishment ID cannot be a zero or negative")
    private Long establishmentId;
}
