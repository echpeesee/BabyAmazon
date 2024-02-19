package com.baby.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer  {
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver exceptionHandler;
	
	@Bean
	public JwtAuthFilter jwtAuthFilter() {
		return  new JwtAuthFilter(exceptionHandler);
	}
  
  @Bean
  public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
	  http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorizeRequests) ->
    {
		try {
			authorizeRequests
			.requestMatchers("/login").permitAll()
			.requestMatchers("/addToCart/**").permitAll()
			.requestMatchers("/buyProduct/**").permitAll()
			.requestMatchers("/allProducts").permitAll()
			.requestMatchers("/productsInCart/**").permitAll()
			.requestMatchers("/productsOrdered/**").permitAll()
			.requestMatchers("/check").authenticated().and().sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(daoAuthenticationProvider()).addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("exception in authorization");
		}
	});

	return http.build();
	
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
	  return NoOpPasswordEncoder.getInstance();
  }
  
  @Bean
  public UserDetailsService userDetailService() {
	  return new MyUserDetailsService();
  }
  
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
	  DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
	  daoAuthProvider.setUserDetailsService(userDetailService());
	  daoAuthProvider.setPasswordEncoder(passwordEncoder());
	  return daoAuthProvider;
  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	  return config.getAuthenticationManager();
  }
}
