package ifba.Olimpiada.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ifba.Olimpiada.dtos.PaisDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "paises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nome;
	public String codigo;
	
	@ManyToMany(mappedBy = "paisesSeguidos")
	@JsonIgnore
	private List<Usuario> seguidores = new ArrayList<>();
	
	@OneToMany(mappedBy = "pais")
	public List<Medalha> medalhas;
	
	public Pais(PaisDto paisDto) {
		this.id = paisDto.id();
		this.nome = paisDto.nome();
		this.codigo = paisDto.codigo();
	}
}
