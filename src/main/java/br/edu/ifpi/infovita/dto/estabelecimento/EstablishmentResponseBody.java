package br.edu.ifpi.infovita.dto.estabelecimento;

import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EstablishmentAddressResponseBody;
import br.edu.ifpi.infovita.dto.estabelecimentoEquipamento.EstablishmentEquipmentResponseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentResponseBody {
    private Long id;
    private String name;
    private String businessName;
    private String cnes;
    private String cnpj;
    private Boolean sus;
    private EstablishmentAddressResponseBody address;
    private List<EstablishmentEquipmentResponseBody> equipments = new ArrayList<>();

    public static EstablishmentResponseBody convertEstablishmentToResponseDto(Establishment establishment){
        return EstablishmentResponseBody.builder()
                .id(establishment.getId())
                .name(establishment.getName())
                .businessName(establishment.getBusinessName())
                .cnes(establishment.getCnes())
                .cnpj(establishment.getCnpj())
                .sus(establishment.getSus())
                .address(EstablishmentAddressResponseBody.builder()
                        .placeId(establishment.getAddress().getPlaceId())
                        .number(establishment.getAddress().getNumber())
                        .district(establishment.getAddress().getDistrict())
                        .complement(establishment.getAddress().getComplement())
                        .publicPlace(establishment.getAddress().getPublicPlace())
                        .city(establishment.getAddress().getCity())
                        .build())
                .equipments(establishment.getEquipments().stream()
                        .map(establishmentEquipment -> EstablishmentEquipmentResponseBody.builder()
                                .id(establishmentEquipment.getEquipment().getId())
                                .name(establishmentEquipment.getEquipment().getName())
                                .existing(establishmentEquipment.getExisting())
                                .functional(establishmentEquipment.getFunctional())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
