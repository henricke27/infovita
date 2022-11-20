package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    @Query(nativeQuery = true, value = "select es.* from establishment es join establishment_equipment eseq on es.id = eseq.establishment_id " +
            "join exam_equipment exes on eseq.equipment_id = exes.equipment_id where exes.exam_id = ?1")
    List<Establishment> findAllByExam(Long exam);
}
