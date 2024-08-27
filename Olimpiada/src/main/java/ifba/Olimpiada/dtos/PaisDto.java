package ifba.Olimpiada.dtos;

import java.util.List;

import ifba.Olimpiada.models.Pais;

public record PaisDto (Long id, String nome, String codigo){

	public PaisDto(Pais pais) {
		this( pais != null ? pais.getId() : null,  pais != null ? pais.getNome() : null,  pais != null ? pais.getCodigo() : null);
	}
}
