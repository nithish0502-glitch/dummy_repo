// package com.examly.springappuser.config;

// import java.util.Arrays;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import com.examly.springappuser.service.UserServiceImpl;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private UserServiceImpl userService;

//     @Autowired
//     private JwtAuthenticationFilter jwtFilter;

//     @Autowired
//     private JwtAuthenticationEntryPoint entryPoint;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         return http.csrf().disable()
//                 .cors().and()
//                 .authorizeHttpRequests()
//                 .requestMatchers("/api/login").permitAll()
//                 .requestMatchers("/api/register").permitAll()
//                 .requestMatchers(HttpMethod.POST, "/api/loan").hasRole("LoanManager")
//                 .requestMatchers(HttpMethod.PUT, "/api/loan/{loanId}").hasRole("")
//                 .requestMatchers(HttpMethod.DELETE, "/api/loan/{loanId}").hasRole("LoanManager")
//                 .requestMatchers(HttpMethod.GET, "/api/loan/{loanId}").hasRole("LoanManager")
//                 .requestMatchers(HttpMethod.GET, "/api/loan").hasAnyRole("LoanManager","User")
//                 .requestMatchers(HttpMethod.GET, "/api/request").hasRole("LoanManager")
//                 .requestMatchers(HttpMethod.PUT, "/api/request/{requestId}").hasRole("BranchManager")
//                 .requestMatchers(HttpMethod.GET, "/api/feedback").hasRole("Admin")
//                 .requestMatchers(HttpMethod.POST, "/api/request").hasRole("User")
//                 .requestMatchers(HttpMethod.GET, "/api/request/user/{userId}").hasRole("User")
//                 .requestMatchers(HttpMethod.DELETE, "/api/request/{requestId}").hasRole("User")
//                 .requestMatchers(HttpMethod.PUT, "/api/feedback/{feedbackId}").hasAnyRole("Admin", "User")
//                 .requestMatchers(HttpMethod.DELETE, "/api/feedback/{feedbackId}").hasRole("User")
//                 .requestMatchers(HttpMethod.POST, "/api/feedback/").hasRole("User")
//                 .requestMatchers(HttpMethod.GET, "/api/feedback/user/{userId}").hasRole("User")
//                 .anyRequest().authenticated().and()
//                 .exceptionHandling()
//                 .authenticationEntryPoint(entryPoint).and()
//                 .sessionManagement()
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                 .authenticationProvider(authenticationProvider())
//                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                 .build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public UserDetailsService userDetailsService() {
//         return new MyUserDetailsService();
//     }

//     @Bean
//     public AuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//         authenticationProvider.setUserDetailsService(userDetailsService());
//         authenticationProvider.setPasswordEncoder(passwordEncoder());
//         return authenticationProvider;
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }

//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList("https://8081-dbaeccff314644937bccfaccecfone.premiumproject.examly.io"));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }



package com.examly.springappuser.config;

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
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

 
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/api/users/**").permitAll() // Open registration
                //.requestMatchers("/api/users/login").hasRole("PetOwner") // Restricted to PetOwner role
                .anyRequest().authenticated() // Secure all other endpoints
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint()); // Handle authentication errors
        return http.build();
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
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
