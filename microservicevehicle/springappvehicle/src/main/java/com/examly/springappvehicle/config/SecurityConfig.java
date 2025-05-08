package com.examly.springapp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import com.examly.springapp.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        return http.csrf().disable()
                .cors().and()
                .authorizeHttpRequests() 
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/api/register").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .and()
                 .authorizeHttpRequests()
                 .requestMatchers(HttpMethod.GET, "/api/{id}").hasAnyRole("USER","ADMIN")
                 .requestMatchers(HttpMethod.GET, "/api/all").hasRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE, "/api/{id}").hasRole("ADMIN")
                 .requestMatchers(HttpMethod.GET, "/api/name/{name}").hasAnyRole("USER","ADMIN")
                 .requestMatchers(HttpMethod.PUT,"/api/update/{userId}").hasAnyRole("USER","ADMIN")
                 .requestMatchers(HttpMethod.POST,"/api/appointment").hasRole("USER")
                 .requestMatchers(HttpMethod.GET,"/api/appointment").hasRole("ADMIN")
                 .requestMatchers(HttpMethod.GET,"/api/appointment/{id}").hasRole("USER")
                 .requestMatchers(HttpMethod.PUT,"/api/appointment/{id}").hasRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE,"/api/appointment/{id}").hasAnyRole("USER","ADMIN")
                 .requestMatchers(HttpMethod.POST,"/api/services").hasRole("ADMIN")
                 .requestMatchers(HttpMethod.GET, "/api/services").hasAnyRole("USER","ADMIN")
                 .requestMatchers(HttpMethod.PUT,"/api/services/{id}").hasRole("ADMIN") 
                 .requestMatchers(HttpMethod.DELETE,"/api/services/{id}").hasRole("ADMIN")
                 .requestMatchers(HttpMethod.GET, "/api/services/{id}").hasAnyRole("USER","ADMIN")
                 .requestMatchers(HttpMethod.GET, "/api/services/service/{serviceName}").hasAnyRole("USER","ADMIN")
                 .requestMatchers(HttpMethod.POST,"/api/feedback").hasRole("USER")
                 .requestMatchers(HttpMethod.GET,"/api/feedback").hasRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE,"/api/feedback/{id}").hasRole("USER")
                 .requestMatchers(HttpMethod.GET, "/api/feedback/user/{id}").hasAnyRole("USER","ADMIN")
                 
                .anyRequest().authenticated().and()
                .sessionManagement() 
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
                .and() 
                .authenticationProvider(authenticationProvider()) 
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
                .build(); 
    } 
    
    // Password Encoding 
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
  
    @Bean
    public UserDetailsService userDetailsService() { 
        return new MyUserDetailsService(); 
    }
    
    
    @Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService()); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
    
    @Bean
   	public CorsConfigurationSource corsConfigurationSource() {
   	    CorsConfiguration configuration = new CorsConfiguration();
   	    configuration.setAllowedOrigins(Arrays.asList("https://8081-cbcddaddcefd322312118dbcfeedfdcacbone.premiumproject.examly.io")); // Allow your frontend's origin
   	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
   	    configuration.setAllowedHeaders(Arrays.asList("*"));
   	    configuration.setAllowCredentials(true);
   	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
   	    source.registerCorsConfiguration("/**", configuration);
   	    return source;
   	    
   	}
}
