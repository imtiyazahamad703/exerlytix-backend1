package com.immutech.ExerLytix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.immutech.ExerLytix.dto.LoginDto;
import com.immutech.ExerLytix.services.MemberService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	MemberService memberService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> {}) // enable CORS
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/api/register",
								"/api/login",
								"/api/forgot-password",
								"/api/reset-password",
								"/api/exercise/**",  // ✅ Exercise allowed
								"/api/**"             // ✅ All /api endpoints allowed for now
						).permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(login -> login.disable())   // disable login form
				.httpBasic(basic -> basic.disable());  // disable basic auth (for REST)

		return http.build();
	}


	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(memberService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

}
