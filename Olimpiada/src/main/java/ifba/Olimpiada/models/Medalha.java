package ifba.Olimpiada.models;

import java.util.List;

import ifba.Olimpiada.dtos.EsporteDto;
import ifba.Olimpiada.dtos.MedalhaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "medalhas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medalha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long Id;
	@Enumerated(EnumType.STRING)
	public TipoMedalha tipoMedalha;
	private String nomeAtleta;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "pais_id")
	public Pais pais;
	
	@ManyToOne
	@JoinColumn(name = "esporte_id")
	public Esporte esporte;
	
	
	public Medalha(MedalhaDto medalhaDto) {
		this.Id = medalhaDto.id();
		this.nomeAtleta = medalhaDto.nomeAtleta();
		this.descricao= medalhaDto.descricao();
		this.esporte = new Esporte(medalhaDto.esporteDto());
		this.pais = new Pais(medalhaDto.paisDto());
		this.tipoMedalha = medalhaDto.tipoMedalha();
	}
	
}
