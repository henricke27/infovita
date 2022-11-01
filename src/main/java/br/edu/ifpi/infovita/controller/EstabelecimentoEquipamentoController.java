package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.domain.EstabelecimentoEquipamento;
import br.edu.ifpi.infovita.dto.EstabelecimentoEquipamentoDeleteRequest;
import br.edu.ifpi.infovita.dto.EstabelecimentoEquipamentoPostRequest;
import br.edu.ifpi.infovita.service.EquipamentoService;
import br.edu.ifpi.infovita.service.EstabelecimentoEquipamentoService;
import br.edu.ifpi.infovita.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping
@EnableWebMvc
@RequiredArgsConstructor
public class EstabelecimentoEquipamentoController {

    private final EstabelecimentoEquipamentoService estabelecimentoEquipamentoService;
    private final EstabelecimentoService estabelecimentoService;
    private final EquipamentoService equipamentoService;

    @PostMapping(path = "/merge")
    public ResponseEntity<Void> addEquipamentToStableshiment(@RequestBody EstabelecimentoEquipamentoPostRequest eepr){
        Estabelecimento estabelecimento = estabelecimentoService.findById(eepr.getEstabelecimento());
        Equipamento equipamento = equipamentoService.findById(eepr.getEquipamento());

        EstabelecimentoEquipamento estabelecimentoEquipamento = EstabelecimentoEquipamento.builder()
                .funcionais(eepr.getFuncionais())
                .existentes(eepr.getExistentes())
                .sus(eepr.getSus())
                .estabelecimento(estabelecimento)
                .equipamento(equipamento)
                .build();

        estabelecimentoEquipamentoService.addEquipamentToStableshiment(estabelecimentoEquipamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/unmerge")
    public ResponseEntity<Void> removeEquipamentToStableshiment(@RequestBody EstabelecimentoEquipamentoDeleteRequest eedr){
        Estabelecimento estabelecimento = estabelecimentoService.findById(eedr.getEstabelecimento());
        Equipamento equipamento = equipamentoService.findById(eedr.getEquipamento());

        estabelecimentoEquipamentoService.removeEquipamentStableshiment(estabelecimento, equipamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
