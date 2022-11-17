package br.edu.ifpi.infovita.dto.exame;

import br.edu.ifpi.infovita.domain.Exam;
import br.edu.ifpi.infovita.dto.equipamento.EquipmentResponseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponseBody {
    private Long id;
    private String name;
    private List<EquipmentResponseBody> equipments = new ArrayList<>();

    public static ExamResponseBody convertExamToResponseDto(Exam exam){
        return ExamResponseBody.builder()
                .id(exam.getId())
                .name(exam.getName())
                .equipments(exam.getEquipment().stream()
                        .map(EquipmentResponseBody::convertEquipamentToResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
