package br.edu.ifpi.infovita.dto.exame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamePutRequestBody {
    private Long id;
    private String nome;
}
