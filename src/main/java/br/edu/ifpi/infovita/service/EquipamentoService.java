package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.repository.EquipamentoRepository;
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
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;

    public Equipamento findById(Long id){
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado!"));
    }

    public Page<Equipamento> findAll(Pageable pageable){
        return equipamentoRepository.findAll(pageable);
    }

    public Equipamento save(Equipamento equipamento){
        return equipamentoRepository.save(equipamento);
    }

    public void deleteById(Long id){
        equipamentoRepository.delete(findById(id));
    }

    public void update(Equipamento equipamento){
        Equipamento equipamentoSaved = findById(equipamento.getId());

        Equipamento equipamentoTobeUpdated = Equipamento.builder()
                .id(equipamentoSaved.getId())
                .nome(equipamento.getNome())
                .build();

        equipamentoRepository.save(equipamentoTobeUpdated);
    }

}