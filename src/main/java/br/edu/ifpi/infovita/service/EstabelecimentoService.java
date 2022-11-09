package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.repository.EstabelecimentoEquipamentoRepository;
import br.edu.ifpi.infovita.repository.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EstabelecimentoService {
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EstabelecimentoEquipamentoRepository estabelecimentoEquipamentoRepository;

    public List<Estabelecimento> findAllByExame(Long idExame){
        return estabelecimentoRepository.findAllByExame(idExame);
    }

    public Estabelecimento findById(Long id){
        return estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado!"));
    }

    public List<Estabelecimento> findAll(){
        return estabelecimentoRepository.findAll();
    }

    public Page<Estabelecimento> findAllPageable(Pageable pageable){
        return estabelecimentoRepository.findAll(pageable);
    }

    public Estabelecimento saveWithAddressCompose(Estabelecimento estabelecimento){
        return estabelecimentoRepository.save(estabelecimento);
    }

    public void deleteById(Long id){
        Estabelecimento estabelecimento = findById(id);
        estabelecimentoEquipamentoRepository.deleteByEstabelecimento(estabelecimento);
        estabelecimentoRepository.delete(estabelecimento);
    }

    public void updateWithAddressCompose(Estabelecimento estabelecimento){
        Estabelecimento estabelecimentoFound = findById(estabelecimento.getId());
        estabelecimento.getEndereco().setId(estabelecimentoFound.getEndereco().getId());

        estabelecimentoRepository.save(estabelecimento);
    }

}
