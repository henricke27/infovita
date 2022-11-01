package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

//    @Query(value = "SELECT e FROM Equipamento e JOIN FETCH e.estabelecimentos ee WHERE ee.estabelecimento = ?1")
//    List<Equipamento> findAllByEstabelecimento(Estabelecimento estabelecimento);
}
