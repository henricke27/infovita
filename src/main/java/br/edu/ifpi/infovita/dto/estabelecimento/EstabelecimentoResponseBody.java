package br.edu.ifpi.infovita.dto.estabelecimento;

import br.edu.ifpi.infovita.domain.Estabelecimento;
import br.edu.ifpi.infovita.dto.enderecoEstabelecimento.EnderecoEstabelecimentoResponseBody;
import br.edu.ifpi.infovita.dto.equipamento.EquipamentoResponseBody;
import br.edu.ifpi.infovita.dto.estabelecimentoEquipamento.EstabelecimentoEquipamentoResponseBody;
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
public class EstabelecimentoResponseBody {
    private Long id;
    private String nome;
    private String nomeEmpresarial;
    private String cnes;
    private String cnpj;
    private Boolean sus;
    private EnderecoEstabelecimentoResponseBody endereco;
    private List<EstabelecimentoEquipamentoResponseBody> equipamentos = new ArrayList<>();

    public static EstabelecimentoResponseBody convertEstabelecimentoToResponseDto(Estabelecimento estabelecimento){
        return EstabelecimentoResponseBody.builder()
                .id(estabelecimento.getId())
                .nome(estabelecimento.getNome())
                .nomeEmpresarial(estabelecimento.getNomeEmpresarial())
                .cnes(estabelecimento.getCnes())
                .cnpj(estabelecimento.getCnpj())
                .sus(estabelecimento.getSus())
                .endereco(EnderecoEstabelecimentoResponseBody.builder()
                        .placeId(estabelecimento.getEndereco().getPlaceId())
                        .numero(estabelecimento.getEndereco().getNumero())
                        .bairro(estabelecimento.getEndereco().getBairro())
                        .complemento(estabelecimento.getEndereco().getComplemento())
                        .logradouro(estabelecimento.getEndereco().getLogradouro())
                        .municipio(estabelecimento.getEndereco().getMunicipio())
                        .build())
                .equipamentos(estabelecimento.getEquipamentos().stream()
                        .map(estabelecimentoEquipamento -> EstabelecimentoEquipamentoResponseBody.builder()
                                .id(estabelecimentoEquipamento.getEquipamento().getId())
                                .nome(estabelecimentoEquipamento.getEquipamento().getNome())
                                .existentes(estabelecimentoEquipamento.getExistentes())
                                .funcionais(estabelecimentoEquipamento.getFuncionais())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
