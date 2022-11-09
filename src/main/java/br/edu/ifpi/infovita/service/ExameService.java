package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Exame;
import br.edu.ifpi.infovita.repository.ExameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ExameService {

    private final EquipamentoService equipamentoService;
    private final ExameRepository exameRepository;

    public Exame findById(Long id){
        return exameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exame não encontrado!"));
    }

    public Page<Exame> findAll(Pageable pageable){
        return exameRepository.findAll(pageable);
    }

    public Exame save(Exame exame){
        return exameRepository.save(exame);
    }

    public void deleteById(Long id){
        Exame Exame = findById(id);
        exameRepository.delete(Exame);
    }

    public void update(Exame exame){
        Exame exameFound = findById(exame.getId());
        exameFound.setNome(exame.getNome());

        exameRepository.save(exameFound);
    }

    public void addEquipamentoToExame(Long exameId, Long equipamentoId){
        Exame exame = findById(exameId);
        Equipamento equipamento = equipamentoService.findById(equipamentoId);

        exame.getEquipamentos().add(equipamento);

        exameRepository.save(exame);
    }

    public void removeEquipamentoFromExame(Long exameId, Long equipamentoId){
        Exame exame = findById(exameId);
        Equipamento equipamento = equipamentoService.findById(equipamentoId);

        exameRepository.deleteByExameAndEquipamento(exame.getId(), equipamento.getId());
    }
}
