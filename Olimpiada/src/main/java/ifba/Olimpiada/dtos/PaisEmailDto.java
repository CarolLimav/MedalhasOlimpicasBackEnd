package ifba.Olimpiada.dtos;

import java.util.List;

import ifba.Olimpiada.models.TipoMedalha;



public record PaisEmailDto(String nomePais, List<String> emails, String nomeAtleta, String descricao, String esporte, TipoMedalha tipoMedalha) {
}
