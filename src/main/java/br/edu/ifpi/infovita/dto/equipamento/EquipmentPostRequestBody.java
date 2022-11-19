package br.edu.ifpi.infovita.dto.equipamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentPostRequestBody {
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
}
