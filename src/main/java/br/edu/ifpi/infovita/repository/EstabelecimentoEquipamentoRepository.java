package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.EstabelecimentoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoEquipamentoRepository extends JpaRepository<EstabelecimentoEquipamento, Long> {
    void deleteByEquipamento(Equipamento equipamento);

    @Modifying
    @Query(value = "delete from estabelecimento_equipamento where estabelecimento_id = ?1 and equipamento_id = ?2", nativeQuery = true)
    void deleteByEstabelecimentoAndEquipamento(Long estabelecimento, Long equipamento);
}
