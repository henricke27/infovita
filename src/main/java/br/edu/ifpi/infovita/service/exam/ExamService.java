package br.edu.ifpi.infovita.service.exam;

import br.edu.ifpi.infovita.domain.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamService {
    Exam findById(Long id);
    Page<Exam> findAll(Pageable pageable);
    Exam save(Exam exam);
    void deleteById(Long id);
    void update(Exam exam);
    void addEquipmentToExam(Long examId, Long equipmentId);
    void removeEquipmentFromExam(Long examId, Long equipmentId);
}
