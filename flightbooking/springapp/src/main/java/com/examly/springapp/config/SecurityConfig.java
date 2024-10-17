package com.examly.springapp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http.csrf().disable()
    //             .cors().and()
    //             .authorizeHttpRequests()
    //             .requestMatchers("/api/login").permitAll()
    //             .requestMatchers("/api/register").permitAll()
    //             .requestMatchers(HttpMethod.POST, "/api/pet").hasAuthority("ROLE_PetOwner") // POST access restricted to PetOwner role
    //             .requestMatchers(HttpMethod.GET, "/api/pet").hasAuthority("ROLE_HospitalAdmin")
    //             .requestMatchers(HttpMethod.GET, "/api/pet/{petId}").hasAnyAuthority("ROLE_HospitalAdmin","ROLE_PetOwner")
    //             .requestMatchers(HttpMethod.POST, "/api/appointments").hasAuthority("ROLE_PetOwner") // POST access restricted to PetOwner role
    //             .requestMatchers(HttpMethod.GET, "/api/appointments").permitAll()
    //             .requestMatchers(HttpMethod.DELETE, "/api/pet").hasAnyAuthority("ROLE_PetOwner") 
    //             .requestMatchers(HttpMethod.GET, "/api/pet/user/{userId}").hasAuthority("ROLE_PetOwner")
    //             .requestMatchers(HttpMethod.GET, "/api/feedback/user/{userId}").hasAuthority("ROLE_PetOwner")
    //             .requestMatchers(HttpMethod.POST, "/api/feedback").hasAuthority("ROLE_PetOwner")
    //             .requestMatchers(HttpMethod.GET, "/api/feedback").hasAuthority("ROLE_HospitalAdmin")
    //             .anyRequest().authenticated().and()
    //             .exceptionHandling()
    //             .authenticationEntryPoint(entryPoint).and()
    //             .sessionManagement()
    //             .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    //             .authenticationProvider(authenticationProvider())
    //             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    //             .build();
    // }

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .cors().and()
            .authorizeHttpRequests()
            .anyRequest().permitAll() // Allow all requests
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(entryPoint).and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}


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
        configuration.setAllowedOrigins(Arrays.asList("https://8081-ddaeaedabebb313990803dacaabedeebtwo.premiumproject.examly.io"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
