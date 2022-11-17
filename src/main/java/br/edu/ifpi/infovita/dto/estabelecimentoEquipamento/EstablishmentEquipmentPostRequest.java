package br.edu.ifpi.infovita.dto.estabelecimentoEquipamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentEquipmentPostRequest {
    @NotNull(message = "Equipment cannot be null") @Positive(message = "Equipment ID cannot be a zero or negative")
    private Long equipmentId;
    @NotNull(message = "Establishment ID cannot be null") @Positive(message = "Establishment ID cannot be a zero or negative")
    private Long establishmentId;
    @PositiveOrZero(message = "Existing cannot be negative")
    private Integer existing;
    @PositiveOrZero(message = "Functional cannot be negative")
    private Integer functional;
}
