package ifba.Olimpiada.dtos;

import java.util.List;

import ifba.Olimpiada.models.Usuario;

public record UsuarioDto(
		Long id,
		String nome, 
		String email,
		String login,
		String senha,
		List<RoleDto> roles) {
	
	public UsuarioDto(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getLogin(), usuario.getSenha(), 
				usuario.getRoles().stream().map(RoleDto::new).toList());
	}

}