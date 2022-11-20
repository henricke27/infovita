package br.edu.ifpi.infovita.dto.equipamento;

import br.edu.ifpi.infovita.domain.Equipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentResponseBody {
    private Long id;
    private String name;

    public static EquipmentResponseBody convertEquipmentToResponseDto(Equipment equipment){
        return EquipmentResponseBody.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .build();
    }
}
