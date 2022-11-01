package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.EnderecoEstabelecimento;
import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.dto.equipamento.EquipamentoResponseBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EnderecoEstabelecimentoResponseBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstabelecimentoResponseBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstabelecimentoPostRequestBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstabelecimentoPutRequestBody;
import br.edu.ifpi.infovita.repository.EstabelecimentoRepository;
import br.edu.ifpi.infovita.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/estabelecimento")
@EnableWebMvc
@RequiredArgsConstructor
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;
    private final EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Estabelecimento> findById(@PathVariable Long id){
        return new ResponseEntity<>(estabelecimentoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstabelecimentoResponseBody>> findAll(){
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        List<EstabelecimentoResponseBody> estabelecimentoResponseBodies = estabelecimentos.stream().map(estabelecimento -> EstabelecimentoResponseBody.builder()
                        .id(estabelecimento.getId())
                        .nome(estabelecimento.getNome())
                        .nomeEmpresarial(estabelecimento.getNomeEmpresarial())
                        .cnes(estabelecimento.getCnes())
                        .cnpj(estabelecimento.getCnpj())
                        .endereco(EnderecoEstabelecimentoResponseBody.builder()
                                .placeId(estabelecimento.getEndereco().getPlaceId())
                                .numero(estabelecimento.getEndereco().getNumero())
                                .bairro(estabelecimento.getEndereco().getBairro())
                                .complemento(estabelecimento.getEndereco().getComplemento())
                                .logradouro(estabelecimento.getEndereco().getLogradouro())
                                .municipio(estabelecimento.getEndereco().getMunicipio())
                                .build())
                        .equipamentos(estabelecimento.getEquipamentos().stream()
                                .map(estabelecimentoEquipamento -> EquipamentoResponseBody.builder()
                                        .id(estabelecimentoEquipamento.getEquipamento().getId())
                                        .nome(estabelecimentoEquipamento.getEquipamento().getNome())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(estabelecimentoResponseBodies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> save(@RequestBody EstabelecimentoPostRequestBody estabelecimento){
        EnderecoEstabelecimentoResponseBody enderecoDto = estabelecimento.getEndereco();
        EnderecoEstabelecimento enderecoToBeSaved = EnderecoEstabelecimento.builder()
                .placeId(enderecoDto.getPlaceId())
                .numero(enderecoDto.getNumero())
                .bairro(enderecoDto.getBairro())
                .complemento(enderecoDto.getComplemento())
                .logradouro(enderecoDto.getLogradouro())
                .municipio(enderecoDto.getMunicipio())
                .build();

        Estabelecimento estabelecimentoToBeSave = Estabelecimento.builder()
                .nome(estabelecimento.getNome())
                .nomeEmpresarial(estabelecimento.getNomeEmpresarial())
                .cnpj(estabelecimento.getCnpj())
                .cnes(estabelecimento.getCnes())
                .endereco(enderecoToBeSaved)
                .equipamentos(new ArrayList<>())
                .build();

        return new ResponseEntity<>(estabelecimentoService.saveWithAddressCompose(estabelecimentoToBeSave), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody EstabelecimentoPutRequestBody estabelecimento){
        EnderecoEstabelecimentoResponseBody enderecoDto = estabelecimento.getEndereco();
        EnderecoEstabelecimento enderecoToBeSaved = EnderecoEstabelecimento.builder()
                .placeId(enderecoDto.getPlaceId())
                .numero(enderecoDto.getNumero())
                .bairro(enderecoDto.getBairro())
                .complemento(enderecoDto.getComplemento())
                .logradouro(enderecoDto.getLogradouro())
                .municipio(enderecoDto.getMunicipio())
                .build();

        Estabelecimento estabelecimentoToBeUpdated = Estabelecimento.builder()
                .id(estabelecimento.getId())
                .nome(estabelecimento.getNome())
                .nomeEmpresarial(estabelecimento.getNomeEmpresarial())
                .cnpj(estabelecimento.getNomeEmpresarial())
                .cnes(estabelecimento.getCnes())
                .endereco(enderecoToBeSaved)
                .build();

        estabelecimentoService.updateWithAddressCompose(estabelecimentoToBeUpdated);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estabelecimentoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
