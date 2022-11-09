package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
    @Modifying
    @Query(value = "delete from exame_equipamento where exame_id = ?1 and equipamento_id = ?2", nativeQuery = true)
    void deleteByExameAndEquipamento(Long exame, Long equipamento);
}
