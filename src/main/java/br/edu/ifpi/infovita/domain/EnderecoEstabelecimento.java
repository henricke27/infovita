package br.edu.ifpi.infovita.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEstabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonIgnore
    private Long id;
    private String placeId;
    private String bairro;
    private String complemento;
    private String municipio;
    private String numero;
    private String logradouro;
}
