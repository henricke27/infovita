package br.edu.ifpi.infovita.dto.equipamento;

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
public class EquipmentPutRequestBody {
    @NotNull(message = "ID cannot be null") @Positive(message = "Equipment ID cannot be a zero or negative")
    private Long id;
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
}
