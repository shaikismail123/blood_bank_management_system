package com.capstone.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capstone.demo.filter.AppFilter;
import com.capstone.demo.service.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AppFilter filter;
	@Autowired
	private MyUserDetailsServiceImpl userDtlsSvc;

	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDtlsSvc);
		authenticationProvider.setPasswordEncoder(pwdEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				// Exclude login/register endpoints from CSRF protection
//				.csrf(csrf -> csrf.ignoringRequestMatchers("/userDetails/login", "/userDetails/registerUser"))
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/userDetails/login", "/userDetails/registerUser", "/swagger-ui/**",
//								"/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
//						.permitAll().anyRequest().authenticated())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.httpBasic(Customizer.withDefaults()) // optional with JWT
//				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
//	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf(customizer -> customizer.disable())
//				.authorizeHttpRequests(request -> request
//						.requestMatchers("/userDetails/login", "/userDetails/registerUser", "/swagger-ui/**",
//								"/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
//						.permitAll().anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
//	}

	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 
		return http.csrf(customizer -> customizer.disable()).
				authorizeHttpRequests(request -> request
						.requestMatchers("/userDetails/login", "/userDetails/registerUser", "/swagger-ui/**",
								"/v3/api-docs/**",
								"/swagger-resources/**",
								"/webjars/**")
						.permitAll()
						.requestMatchers("/admin/**")
						.hasAuthority("ADMIN")
						.requestMatchers("/requester/**")
						.hasAuthority("REQUESTER")
						.requestMatchers("/donar/**")
						.hasAuthority("DONAR")
						.anyRequest().authenticated())
				.httpBasic(AbstractHttpConfigurer::disable)
				.       sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	
}