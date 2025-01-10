package com.training.springsecurity.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.training.springsecurity.security.jwt.AuthEntryPointJwt;
import com.training.springsecurity.security.jwt.AuthTokenFilter;
import com.training.springsecurity.service.UserDetailsServiceImpl;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
public class SecurityConfig {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	public final static String[] PUBLIC_REQUEST_MATCHERS = { "/api/auth/**","/swagger-ui/**","/v3/api-docs/**"};
    
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    @Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
   
    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers(
                        antMatcher("/h2-console/**")).permitAll()
                    .requestMatchers(PUBLIC_REQUEST_MATCHERS).permitAll()
                    .requestMatchers("/greet").hasRole("USER")
                    .requestMatchers("/api/customer/customers").hasAnyRole("USER", "ADMIN") 
                    .requestMatchers("/api/customer/get/**").hasAnyRole("USER", "ADMIN") 
                    .requestMatchers("/api/customer/update/**").hasRole("ADMIN")
                    .requestMatchers("/api/customer/delete/**").hasRole("ADMIN") 
                    .requestMatchers("/api/customer/new").hasRole("ADMIN") 
                    .requestMatchers("/admingreet").hasRole("ADMIN");
            })
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Allow frames for H2
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




	/*
	 * @Bean public UserDetailsService userDetailsService(PasswordEncoder
	 * passwordEncoder) { // Define in-memory users InMemoryUserDetailsManager
	 * manager = new InMemoryUserDetailsManager();
	 * manager.createUser(User.withUsername("user")
	 * .password(passwordEncoder.encode("password")) .roles("USER") .build());
	 * manager.createUser(User.withUsername("admin")
	 * .password(passwordEncoder.encode("admin")) .roles("ADMIN") .build()); return
	 * manager; }
	 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCrypt password encoder
        return new BCryptPasswordEncoder();
    }
   
    
}
