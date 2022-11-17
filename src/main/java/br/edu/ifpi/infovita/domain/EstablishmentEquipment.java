package br.edu.ifpi.infovita.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentEquipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JsonIgnore
    private Establishment establishment;
    @ManyToOne @JsonIgnore
    private Equipment equipment;
    private Integer existing;
    private Integer functional;
}
