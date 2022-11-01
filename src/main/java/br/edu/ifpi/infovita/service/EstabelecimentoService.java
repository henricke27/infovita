package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.repository.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
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

    public Estabelecimento findById(Long id){
        return estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado!"));
    }

    public List<Estabelecimento> findAll(){
        return estabelecimentoRepository.findAll();
    }

    public Estabelecimento saveWithAddressCompose(Estabelecimento estabelecimento){
        return estabelecimentoRepository.save(estabelecimento);
    }

    public void deleteById(Long id){
        estabelecimentoRepository.delete(findById(id));
    }

    public void updateWithAddressCompose(Estabelecimento estabelecimento){
        Estabelecimento estabelecimentoFound = findById(estabelecimento.getId());
        estabelecimento.getEndereco().setId(estabelecimentoFound.getEndereco().getId());

        Estabelecimento estabelecimentoToBeUpdated = Estabelecimento.builder()
                .id(estabelecimentoFound.getId())
                .nome(estabelecimento.getNome())
                .nomeEmpresarial(estabelecimento.getNomeEmpresarial())
                .cnes(estabelecimento.getCnes())
                .cnpj(estabelecimento.getCnpj())
                .endereco(estabelecimento.getEndereco())
                .build();

        estabelecimentoRepository.save(estabelecimentoToBeUpdated);
    }

}
