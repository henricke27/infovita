package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.domain.EstabelecimentoEquipamento;
import br.edu.ifpi.infovita.repository.EstabelecimentoEquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EstabelecimentoEquipamentoService {
    private final EstabelecimentoEquipamentoRepository estabelecimentoEquipamentoRepository;

    public void addEquipamentToStableshiment(EstabelecimentoEquipamento estabelecimentoEquipamento){
        estabelecimentoEquipamentoRepository.save(estabelecimentoEquipamento);
    }

    public void removeEquipamentStableshiment(Estabelecimento estabelecimento, Equipamento equipamento){
        estabelecimentoEquipamentoRepository.deleteByEstabelecimentoAndEquipamento(estabelecimento.getId(), equipamento.getId());
    }

    public void deleteByEquipamento(Equipamento equipamento){
        estabelecimentoEquipamentoRepository.deleteByEquipamento(equipamento);
    }

}
