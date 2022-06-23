package it.rjcsoft.atlantis.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.rjcsoft.atlantis.model.SrvUtentiSgcsl;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Boolean expiration;

	@Value("${jwt.reset.expiration}")
	private Boolean jwtresetexpiration;

	@Value("${jwt.expiration.seconds}")
	private Long jwtexpirationseconds;

	@Value("${jwt.reset.expiration.seconds}")
	private Long jwtresetexpirationseconds;

	public SrvUtentiSgcsl parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

			if (body.getExpiration()!= null) {
				Date now = new Date();
				Date exp = body.getExpiration();
				if (now.after(exp)) {
					return null;
				}
			}

			SrvUtentiSgcsl u = new SrvUtentiSgcsl();
			u.setLOGIN(body.getSubject());
			u.setPSW(((String) body.get("password")));
			return u;

		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	public String generateToken(SrvUtentiSgcsl user) {
		Claims claims = Jwts.claims().setSubject(user.getLOGIN());
		claims.put("password", new String(user.getPSW()));
		if (expiration) {
			Date now = new Date();
			Date exp = new Date(now.getTime() + jwtexpirationseconds * 1000);
			claims.setExpiration(exp);
		}
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String generateTokenEmail(String email) {
		Claims claims = Jwts.claims().setSubject(email);
		if (jwtresetexpiration) {
			Date now = new Date();
			Date exp = new Date(now.getTime() + jwtresetexpirationseconds * 1000);
			claims.setExpiration(exp);
		}
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String parseTokenEmail(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			if (body.getExpiration()!= null) {
				Date now = new Date();
				Date exp = body.getExpiration();
				if (now.after(exp)) {
					return null;
				}
			}
			return body.getSubject();
		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}
}