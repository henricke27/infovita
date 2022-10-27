package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/estabelecimento")
@RequiredArgsConstructor
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @GetMapping(path = "{id}")
    public ResponseEntity<Estabelecimento> findById(@PathVariable Long id){
        return new ResponseEntity<>(estabelecimentoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Estabelecimento>> findAll(Pageable pageable){
        return new ResponseEntity<>(estabelecimentoService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> save(@RequestBody Estabelecimento estabelecimento){
        return new ResponseEntity<>(estabelecimentoService.save(estabelecimento), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Estabelecimento estabelecimento){
        estabelecimentoService.update(estabelecimento);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estabelecimentoService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
