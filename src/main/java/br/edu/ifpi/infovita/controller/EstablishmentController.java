package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.domain.EstablishmentAddress;
import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EstablishmentAddressRequestBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstablishmentPostRequestBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstablishmentPutRequestBody;
import br.edu.ifpi.infovita.dto.estabelecimento.EstablishmentResponseBody;
import br.edu.ifpi.infovita.service.establishment.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/establishment")
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @GetMapping(path = "/find-by-exam/{id}")
    public ResponseEntity<List<EstablishmentResponseBody>> findAllByExame(@PathVariable Long id){
        List<Establishment> establishments = establishmentService.findAllByExam(id);
        List<EstablishmentResponseBody> establishmentResponseBodies = establishments.stream()
                .map(EstablishmentResponseBody::convertEstablishmentToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(establishmentResponseBodies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EstablishmentResponseBody> findById(@PathVariable Long id){
        Establishment establishment = establishmentService.findById(id);
        EstablishmentResponseBody establishmentResponseBody = EstablishmentResponseBody.convertEstablishmentToResponseDto(establishment);

        return new ResponseEntity<>(establishmentResponseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstablishmentResponseBody>> findAll(){
        List<Establishment> establishments = establishmentService.findAll();
        List<EstablishmentResponseBody> establishmentResponseBodies = establishments.stream()
                .map(EstablishmentResponseBody::convertEstablishmentToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(establishmentResponseBodies, HttpStatus.OK);
    }

    @GetMapping("/on-page")
    public ResponseEntity<List<EstablishmentResponseBody>> findAllPageable(Pageable pageable){
        Page<Establishment> establishments = establishmentService.findAllPageable(pageable);
        List<EstablishmentResponseBody> establishmentResponseBodies = establishments.getContent().stream()
                .map(EstablishmentResponseBody::convertEstablishmentToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(establishmentResponseBodies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstablishmentResponseBody> save(@RequestBody @Valid EstablishmentPostRequestBody establishment){
        EstablishmentAddressRequestBody addressDto = establishment.getAddress();
        EstablishmentAddress addressToBeSaved = EstablishmentAddress.builder()
                .placeId(addressDto.getPlaceId())
                .number(addressDto.getNumber())
                .district(addressDto.getDistrict())
                .complement(addressDto.getComplement())
                .publicPlace(addressDto.getPublicPlace())
                .city(addressDto.getCity())
                .build();

        Establishment establishmentToBeSave = Establishment.builder()
                .name(establishment.getName())
                .businessName(establishment.getBusinessName())
                .cnpj(establishment.getCnpj())
                .cnes(establishment.getCnes())
                .sus(establishment.getSus())
                .address(addressToBeSaved)
                .equipments(new ArrayList<>())
                .build();

        Establishment establishmentSaved = establishmentService.saveWithAddressCompose(establishmentToBeSave);
        EstablishmentResponseBody establishmentResponseBody = EstablishmentResponseBody.convertEstablishmentToResponseDto(establishmentSaved);

        return new ResponseEntity<>(establishmentResponseBody, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid EstablishmentPutRequestBody establishment){
        EstablishmentAddressRequestBody addressDto = establishment.getAddress();
        EstablishmentAddress addressToBeUpdated = EstablishmentAddress.builder()
                .placeId(addressDto.getPlaceId())
                .number(addressDto.getNumber())
                .district(addressDto.getDistrict())
                .complement(addressDto.getComplement())
                .publicPlace(addressDto.getPublicPlace())
                .city(addressDto.getCity())
                .build();

        Establishment establishmentToBeUpdated = Establishment.builder()
                .id(establishment.getId())
                .name(establishment.getName())
                .businessName(establishment.getBusinessName())
                .cnpj(establishment.getBusinessName())
                .cnes(establishment.getCnes())
                .sus(establishment.getSus())
                .address(addressToBeUpdated)
                .build();

        establishmentService.updateWithAddressCompose(establishmentToBeUpdated);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        establishmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
