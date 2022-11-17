package br.edu.ifpi.infovita.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Establishment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String businessName;
    private String cnes;
    private String cnpj;
    private Boolean sus;

    @OneToOne(cascade = CascadeType.ALL)
    private EstablishmentAddress address;

    @OneToMany(mappedBy = "establishment")
    private List<EstablishmentEquipment> equipments = new ArrayList<>();
}
