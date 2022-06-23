package it.rjcsoft.atlantis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import it.rjcsoft.atlantis.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("it.rjcsoft.*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeRequests()
				.antMatchers("/utente/**","/actuator/**","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
						"/swagger-ui/*", "/webjars/**", "/test/**")
				.permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
		// .permitAll();
		// .and().authorizeRequests().anyRequest().authenticated().and().oauth2ResourceServer().jwt();

	}

}