package it.rjcsoft.atlantis.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import it.rjcsoft.atlantis.model.SrvUtentiSgcsl;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5499693478958096376L;

	private String jwtToken;

    private SrvUtentiSgcsl utente = new SrvUtentiSgcsl();
    
    public JwtAuthenticationToken(String jwtToken) {
		super(null);
		this.jwtToken = jwtToken;
	}

	public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, SrvUtentiSgcsl utente,  String jwtToken) {
		super(authorities);
		this.utente = utente;
		this.jwtToken = jwtToken;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return utente;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public SrvUtentiSgcsl getUtente() {
		return utente;
	}
	
}
