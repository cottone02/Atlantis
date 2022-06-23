package it.rjcsoft.atlantis.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	private static final Log LOG = LogFactory.getLog(JwtAuthenticationFilter.class);

	public static final String TOKEN_TYPE = "Bearer ";

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LOG.info("JwtAuthenticationFilter -- request: " + request.getRequestURI());
		if (request.getMethod().equals("OPTIONS")) {
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		} else {
			JwtAuthenticationToken auth = null;
			try {
				String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
				if (jwtToken == null || !jwtToken.startsWith(TOKEN_TYPE)) {
					LOG.warn("Token is not valid");
				} else {
					jwtToken = jwtToken.substring(7);
					if (StringUtils.isEmpty(jwtToken)) {
						LOG.warn("Token Jwt non fornito!");
					} else {
						auth = (JwtAuthenticationToken) this.getAuthenticationManager()
								.authenticate(new JwtAuthenticationToken(jwtToken));
					}
				}
			} catch (Exception ex) {
				LOG.error("Errore durante autenticazione: " + ex.getMessage(), ex);
				throw new AuthenticationServiceException("Errore durante autenticazione : " + ex.getMessage(), ex);
			}
			if (auth != null && auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				LOG.debug("Utente autenticato");
			} else {
				SecurityContextHolder.getContext().setAuthentication(null);
				LOG.debug("Utente non riconosciuto");
			}
		}

		filterChain.doFilter(request, response);
	}
}