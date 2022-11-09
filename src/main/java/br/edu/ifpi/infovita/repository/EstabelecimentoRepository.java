package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

    @Query("SELECT e FROM Estabelecimento e JOIN e.endereco JOIN e.equipamentos")
    List<Estabelecimento> findAllEstabelecimento();

    @Query(nativeQuery = true, value = "select es.* from estabelecimento es join estabelecimento_equipamento eseq on es.id = eseq.estabelecimento_id " +
            "join exame_equipamento exes on eseq.equipamento_id = exes.equipamento_id where exes.exame_id = ?1")
    List<Estabelecimento> findAllByExame(Long exame);
}
