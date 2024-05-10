package com.ipartek.formacion.recetas.configuraciones;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	// https://spring.io/guides/gs/securing-web
	// https://www.baeldung.com/spring-security-jdbc-authentication

	// AUTENTICACIÓN
	private DataSource dataSource;
	
	public WebSecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
		throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery(
				"""
				SELECT email,password,1
				FROM usuarios
				WHERE email = ?
				"""
			)
			.authoritiesByUsernameQuery(
				"""
				SELECT email, CONCAT('ROLE_',rol)
				FROM recetas.usuarios
				WHERE email = ?
				"""
			);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(x -> x.disable()).authorizeHttpRequests(
				requests -> requests
					.requestMatchers("/ingrediente").hasRole("ADMIN")
					.requestMatchers("/css/**", "/js/**").permitAll()
					.requestMatchers("/api/**").permitAll()
					.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll()
				)
				.logout(LogoutConfigurer::permitAll)
				;

		return http.build();
	}
}