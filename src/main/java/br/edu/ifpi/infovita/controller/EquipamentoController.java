package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.dto.equipamento.EquipamentoPostRequestBody;
import br.edu.ifpi.infovita.dto.equipamento.EquipamentoPutRequestBody;
import br.edu.ifpi.infovita.dto.equipamento.EquipamentoResponseBody;
import br.edu.ifpi.infovita.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/equipamento")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<EquipamentoResponseBody> findById(@PathVariable Long id){
        Equipamento equipamento = equipamentoService.findById(id);
        EquipamentoResponseBody equipamentoResponseBody = EquipamentoResponseBody.convertEquipamentToResponseDto(equipamento);

        return new ResponseEntity<>(equipamentoResponseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EquipamentoResponseBody>> findAll(Pageable pageable){
        List<Equipamento> equipamentos = equipamentoService.findAll(pageable).getContent();
        List<EquipamentoResponseBody> equipamentoResponseBodies = equipamentos.stream()
                .map(EquipamentoResponseBody::convertEquipamentToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(equipamentoResponseBodies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipamentoResponseBody> save(@RequestBody EquipamentoPostRequestBody equipamento){
        Equipamento equipamentoToBeSaved = Equipamento.builder()
                .nome(equipamento.getNome())
                .build();

        Equipamento equipamentoSaved = equipamentoService.save(equipamentoToBeSaved);
        EquipamentoResponseBody equipamentoResponseBody = EquipamentoResponseBody.convertEquipamentToResponseDto(equipamentoSaved);

        return new ResponseEntity<>(equipamentoResponseBody, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody EquipamentoPutRequestBody equipamento){
        Equipamento equipamentoToBeUpdated = Equipamento.builder()
                .id(equipamento.getId())
                .nome(equipamento.getNome())
                .build();

        equipamentoService.update(equipamentoToBeUpdated);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        equipamentoService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
