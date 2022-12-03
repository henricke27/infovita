package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.dto.equipamento.EquipmentPostRequestBody;
import br.edu.ifpi.infovita.dto.equipamento.EquipmentPutRequestBody;
import br.edu.ifpi.infovita.dto.equipamento.EquipmentResponseBody;
import br.edu.ifpi.infovita.service.equipment.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping(path = "/by-name/{name}")
    public ResponseEntity<List<EquipmentResponseBody>> findAllByName(@PathVariable String name){
        List<Equipment> equipment = equipmentService.findAllByName(name);
        List<EquipmentResponseBody> equipmentResponseBodies = equipment.stream()
                .map(EquipmentResponseBody::convertEquipmentToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(equipmentResponseBodies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EquipmentResponseBody> findById(@PathVariable Long id){
        Equipment equipment = equipmentService.findById(id);
        EquipmentResponseBody equipmentResponseBody = EquipmentResponseBody.convertEquipmentToResponseDto(equipment);

        return new ResponseEntity<>(equipmentResponseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResponseBody>> findAll(Pageable pageable){
        List<Equipment> equipment = equipmentService.findAll(pageable).getContent();
        List<EquipmentResponseBody> equipmentResponseBodies = equipment.stream()
                .map(EquipmentResponseBody::convertEquipmentToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(equipmentResponseBodies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipmentResponseBody> save(@RequestBody @Valid EquipmentPostRequestBody equipment){
        Equipment equipmentToBeSaved = Equipment.builder()
                .name(equipment.getName())
                .build();

        Equipment equipmentSaved = equipmentService.save(equipmentToBeSaved);
        EquipmentResponseBody equipmentResponseBody = EquipmentResponseBody.convertEquipmentToResponseDto(equipmentSaved);

        return new ResponseEntity<>(equipmentResponseBody, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid EquipmentPutRequestBody equipment){
        Equipment equipmentToBeUpdated = Equipment.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .build();

        equipmentService.update(equipmentToBeUpdated);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        equipmentService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
