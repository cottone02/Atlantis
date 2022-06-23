package it.rjcsoft.atlantis.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import it.rjcsoft.atlantis.model.SrvUtentiSgcsl;
import it.rjcsoft.atlantis.service.SrvUtentiSgcslService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

	@Autowired
	SrvUtentiSgcslService srvUtentiSgcslService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;
			JwtAuthenticationToken auth = new JwtAuthenticationToken(null);
			SrvUtentiSgcsl profiloUtente = jwtUtil.parseToken(jwtAuthToken.getJwtToken());
			if (profiloUtente != null) {
				SrvUtentiSgcsl utente = srvUtentiSgcslService.getProfiloUtente(profiloUtente);
				if (utente != null) {
					auth = new JwtAuthenticationToken(null, profiloUtente, jwtAuthToken.getJwtToken());
					auth.setAuthenticated(true);
				} else {
					LOG.info("Non è possibile recuperare il profilo per l'utente chiamante");
				}
			} else {
				LOG.info("Non è possibile recuperare l'utente chiamante dal token");
			}
			return auth;

		} catch (Exception ex) {
			LOG.error("Errore durante autenticazione: " + ex.getMessage(), ex);
			throw new AuthenticationServiceException("Errore durante autenticazione : " + ex.getMessage(), ex);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JwtAuthenticationToken.class);
	}

}
