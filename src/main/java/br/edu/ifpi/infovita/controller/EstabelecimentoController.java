package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.EnderecoEstabelecimento;
import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EnderecoEstabelecimentoRequestBody;
import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EnderecoEstabelecimentoResponseBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstabelecimentoPostRequestBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstabelecimentoPutRequestBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstabelecimentoResponseBody;
import br.edu.ifpi.infovita.dto.estabelecimentoEquipamento.EstabelecimentoEquipamentoResponseBody;
import br.edu.ifpi.infovita.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<EstabelecimentoResponseBody> findById(@PathVariable Long id){
        Estabelecimento estabelecimento = estabelecimentoService.findById(id);
        EstabelecimentoResponseBody estabelecimentoResponseBody = convertEstabelecimentoToResponseDto(estabelecimento);

        return new ResponseEntity<>(estabelecimentoResponseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstabelecimentoResponseBody>> findAll(){
        List<Estabelecimento> estabelecimentos = estabelecimentoService.findAll();
        List<EstabelecimentoResponseBody> estabelecimentoResponseBodies = estabelecimentos.stream()
                .map(this::convertEstabelecimentoToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(estabelecimentoResponseBodies, HttpStatus.OK);
    }

    @GetMapping("/on-page")
    public ResponseEntity<List<EstabelecimentoResponseBody>> findAllPageable(Pageable pageable){
        Page<Estabelecimento> estabelecimentos = estabelecimentoService.findAllPageable(pageable);
        List<EstabelecimentoResponseBody> estabelecimentoResponseBodies = estabelecimentos.getContent().stream()
                .map(this::convertEstabelecimentoToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(estabelecimentoResponseBodies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> save(@RequestBody EstabelecimentoPostRequestBody estabelecimento){
        EnderecoEstabelecimentoRequestBody enderecoDto = estabelecimento.getEndereco();
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
                .sus(estabelecimento.getSus())
                .endereco(enderecoToBeSaved)
                .equipamentos(new ArrayList<>())
                .build();

        return new ResponseEntity<>(estabelecimentoService.saveWithAddressCompose(estabelecimentoToBeSave), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody EstabelecimentoPutRequestBody estabelecimento){
        EnderecoEstabelecimentoRequestBody enderecoDto = estabelecimento.getEndereco();
        EnderecoEstabelecimento enderecoToBeUpdated = EnderecoEstabelecimento.builder()
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
                .sus(estabelecimento.getSus())
                .endereco(enderecoToBeUpdated)
                .build();

        estabelecimentoService.updateWithAddressCompose(estabelecimentoToBeUpdated);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estabelecimentoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private EstabelecimentoResponseBody convertEstabelecimentoToResponseDto(Estabelecimento estabelecimento){
        return EstabelecimentoResponseBody.builder()
                .id(estabelecimento.getId())
                .nome(estabelecimento.getNome())
                .nomeEmpresarial(estabelecimento.getNomeEmpresarial())
                .cnes(estabelecimento.getCnes())
                .cnpj(estabelecimento.getCnpj())
                .sus(estabelecimento.getSus())
                .endereco(EnderecoEstabelecimentoResponseBody.builder()
                        .placeId(estabelecimento.getEndereco().getPlaceId())
                        .numero(estabelecimento.getEndereco().getNumero())
                        .bairro(estabelecimento.getEndereco().getBairro())
                        .complemento(estabelecimento.getEndereco().getComplemento())
                        .logradouro(estabelecimento.getEndereco().getLogradouro())
                        .municipio(estabelecimento.getEndereco().getMunicipio())
                        .build())
                .equipamentos(estabelecimento.getEquipamentos().stream()
                        .map(estabelecimentoEquipamento -> EstabelecimentoEquipamentoResponseBody.builder()
                                .id(estabelecimentoEquipamento.getEquipamento().getId())
                                .nome(estabelecimentoEquipamento.getEquipamento().getNome())
                                .existentes(estabelecimentoEquipamento.getExistentes())
                                .funcionais(estabelecimentoEquipamento.getFuncionais())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
