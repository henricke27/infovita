package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/equipamento")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @GetMapping
    public ResponseEntity<Page<Equipamento>> findAll(Pageable pageable){
        return new ResponseEntity<>(equipamentoService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Equipamento> save(@RequestBody Equipamento equipamento){
        return new ResponseEntity<>(equipamentoService.save(equipamento), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Equipamento equipamento){
        equipamentoService.update(equipamento);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        equipamentoService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
