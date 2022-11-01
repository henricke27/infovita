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
}
