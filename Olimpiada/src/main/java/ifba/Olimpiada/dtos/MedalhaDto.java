package ifba.Olimpiada.dtos;

import ifba.Olimpiada.models.Medalha;
import ifba.Olimpiada.models.TipoMedalha;

public record MedalhaDto (Long id, String nomeAtleta, String descricao, PaisDto paisDto, EsporteDto esporteDto, TipoMedalha tipoMedalha){

	public MedalhaDto(Medalha medalha) {
		this(medalha.getId(), medalha.getNomeAtleta(), medalha.getDescricao(), new PaisDto(medalha.getPais()), new EsporteDto(medalha.getEsporte()), medalha.getTipoMedalha());
	}

}
