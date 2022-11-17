package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.domain.EstablishmentEquipment;
import br.edu.ifpi.infovita.dto.estabelecimentoEquipamento.EstablishmentEquipmentDeleteRequest;
import br.edu.ifpi.infovita.dto.estabelecimentoEquipamento.EstablishmentEquipmentPostRequest;
import br.edu.ifpi.infovita.service.EstablishmentEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@RestController
@RequestMapping("/establishment")
@EnableWebMvc
@RequiredArgsConstructor
public class EstablishmentEquipmentController {

    private final EstablishmentEquipmentService establishmentEquipmentService;

    @PostMapping(path = "/add-equipment")
    public ResponseEntity<Void> addEquipmentToStableshment(@RequestBody @Valid EstablishmentEquipmentPostRequest eepr){
        Establishment establishment = Establishment.builder()
                .id(eepr.getEstablishmentId())
                .build();

        Equipment equipment = Equipment.builder()
                .id(eepr.getEquipmentId())
                .build();

        EstablishmentEquipment establishmentEquipment = EstablishmentEquipment.builder()
                .functional(eepr.getFunctional())
                .existing(eepr.getExisting())
                .establishment(establishment)
                .equipment(equipment)
                .build();

        establishmentEquipmentService.addEquipmentToEstablishment(establishmentEquipment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/remove-equipment")
    public ResponseEntity<Void> deleteByEquipmentAndStableshment(@RequestBody @Valid EstablishmentEquipmentDeleteRequest eedr){
        Establishment establishment = Establishment.builder()
                .id(eedr.getEstablishmentId())
                .build();

        Equipment equipment = Equipment.builder()
                .id(eedr.getEquipmentId())
                .build();

        establishmentEquipmentService.deleteByEstablishmentAndEquipment(establishment, equipment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
