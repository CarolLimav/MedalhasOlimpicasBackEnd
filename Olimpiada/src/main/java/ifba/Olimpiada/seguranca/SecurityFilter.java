package ifba.Olimpiada.seguranca;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ifba.Olimpiada.repositories.UsuarioRepository;

import ifba.Olimpiada.services.JWTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private JWTokenService tokenService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Filtrando");
		var token = recuperarToken(request);
		
		if (token != null) {
			System.out.println("Token: " + token);
			var login = tokenService.getSubject(token);
			System.out.println("Login: " + login);
			var usuario = usuarioRepository.findByLogin(login);
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} 
		filterChain.doFilter(request, response);
		
	}
	
	public String recuperarToken(HttpServletRequest request) {
		var token = request.getHeader("Authorization");
		//System.out.println("recuperando: " + token);
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;	
		}
		return token.replace("Bearer ", "");
	}

}
	 




