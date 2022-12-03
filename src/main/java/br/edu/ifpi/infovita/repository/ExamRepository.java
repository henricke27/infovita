package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Modifying
    @Query(value = "delete from exam_equipment where exam_id = ?1 and equipment_id = ?2", nativeQuery = true)
    void deleteByExamAndEquipment(Long exam, Long equipment);

    List<Exam> findAllByNameContainingIgnoreCase(String name);
}
