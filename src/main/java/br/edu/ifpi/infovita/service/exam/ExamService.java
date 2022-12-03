package br.edu.ifpi.infovita.service.exam;

import br.edu.ifpi.infovita.domain.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamService {
    List<Exam> findAllByName(String name);
    Exam findById(Long id);
    Page<Exam> findAll(Pageable pageable);
    Exam save(Exam exam);
    void deleteById(Long id);
    void update(Exam exam);
    void addEquipmentToExam(Long examId, Long equipmentId);
    void removeEquipmentFromExam(Long examId, Long equipmentId);
}
