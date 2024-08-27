package ifba.Olimpiada.dtos;

import ifba.Olimpiada.models.Role;

public record RoleDto(Long id, String role) {
	
	public RoleDto(Role role) {
		this(role.getId(), role.getRole());
	}

}
