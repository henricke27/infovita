package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Exame;
import br.edu.ifpi.infovita.dto.exame.ExamePostRequestBody;
import br.edu.ifpi.infovita.dto.exame.ExamePutRequestBody;
import br.edu.ifpi.infovita.dto.exame.ExameResponseBody;
import br.edu.ifpi.infovita.dto.exameEquipamento.ExameEquipamentoDeleteRequestBody;
import br.edu.ifpi.infovita.dto.exameEquipamento.ExameEquipamentoPostRequestBody;
import br.edu.ifpi.infovita.service.ExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/exame")
@RequiredArgsConstructor
public class ExameController {

    private final ExameService exameService;

    @PostMapping(path = "/add-equipamento")
    public ResponseEntity<Void> addEquipamentoToExame(@RequestBody ExameEquipamentoPostRequestBody eeprb){
        exameService.addEquipamentoToExame(eeprb.getExame(), eeprb.getEquipamento());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/remove-equipamento")
    public ResponseEntity<Void> removeEquipamentoFromExame(@RequestBody ExameEquipamentoDeleteRequestBody eedrb){
        exameService.removeEquipamentoFromExame(eedrb.getExame(), eedrb.getEquipamento());

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExameResponseBody> findById(@PathVariable Long id){
        Exame exame = exameService.findById(id);
        ExameResponseBody exameResponseBody = ExameResponseBody.convertExameToResponseDto(exame);

        return new ResponseEntity<>(exameResponseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExameResponseBody>> findAll(Pageable pageable){
        List<Exame> exames = exameService.findAll(pageable).getContent();
        List<ExameResponseBody> exameResponseBodies = exames.stream()
                .map(ExameResponseBody::convertExameToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(exameResponseBodies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExameResponseBody> save(@RequestBody ExamePostRequestBody exame){
        Exame exameToBeSaved = Exame.builder()
                .nome(exame.getNome())
                .build();

        Exame exameSaved = exameService.save(exameToBeSaved);
        ExameResponseBody exameResponseBody = ExameResponseBody.convertExameToResponseDto(exameSaved);

        return new ResponseEntity<>(exameResponseBody, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ExamePutRequestBody exame){
        Exame exameToBeUpdated = Exame.builder()
                .id(exame.getId())
                .nome(exame.getNome())
                .build();

        exameService.update(exameToBeUpdated);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        exameService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
