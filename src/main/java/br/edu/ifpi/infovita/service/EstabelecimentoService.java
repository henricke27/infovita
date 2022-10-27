package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.repository.EnderecoRepository;
import br.edu.ifpi.infovita.repository.EstabelecimentoRepository;
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
public class EstabelecimentoService {
    private final EstabelecimentoRepository estabelecimentoRepository;

    public Estabelecimento findById(Long id){
        return estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado!"));
    }

    public Page<Estabelecimento> findAll(Pageable pageable){
        return estabelecimentoRepository.findAll(pageable);
    }

    public Estabelecimento save(Estabelecimento estabelecimento){
        Estabelecimento estabelecimentoToBeSaved = Estabelecimento.builder()
                .nome(estabelecimento.getNome())
                .nome_empresarial(estabelecimento.getNome_empresarial())
                .cnes(estabelecimento.getCnes())
                .cnpj(estabelecimento.getCnpj())
                .endereco(estabelecimento.getEndereco())
                .build();

        return estabelecimentoRepository.save(estabelecimentoToBeSaved);
    }

    public void deleteById(Long id){
        estabelecimentoRepository.delete(findById(id));
    }

    public void update(Estabelecimento estabelecimento){
        Estabelecimento estabelecimentoFound = findById(estabelecimento.getId());
        estabelecimento.getEndereco().setId(estabelecimentoFound.getEndereco().getId());

        Estabelecimento estabelecimentoToBeUpdated = Estabelecimento.builder()
                .id(estabelecimentoFound.getId())
                .nome(estabelecimento.getNome())
                .nome_empresarial(estabelecimento.getNome_empresarial())
                .cnes(estabelecimento.getCnes())
                .cnpj(estabelecimento.getCnpj())
                .endereco(estabelecimento.getEndereco())
                .build();

        estabelecimentoRepository.save(estabelecimentoToBeUpdated);
    }
}
