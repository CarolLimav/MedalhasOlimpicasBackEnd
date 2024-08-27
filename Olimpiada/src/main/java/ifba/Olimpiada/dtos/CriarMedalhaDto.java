package ifba.Olimpiada.dtos;

import ifba.Olimpiada.models.TipoMedalha;

public record CriarMedalhaDto(Long paisId, String nomeAtleta, String descricao, Long esporteId, TipoMedalha tipoMedalha) {

}



/// 