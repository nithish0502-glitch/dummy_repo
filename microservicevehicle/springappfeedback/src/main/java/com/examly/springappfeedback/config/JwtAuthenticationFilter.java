package com.examly.springappfeedback.config;

import com.examly.springappfeedback.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtValidationService jwtValidationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        String requestTokenHeader = request.getHeader("Authorization");
    
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
    
            try {
                logger.info("Received JWT Token: " + jwtToken);
                if (jwtValidationService.validateToken(jwtToken)) {
                    // Extract user details and roles from token
                    UserDetails userDetails = jwtValidationService.getUserDetailsFromToken(jwtToken);
    
                    if (userDetails != null) {
                        // Log user details and roles
                        logger.info("User authenticated: " + userDetails.getUsername());
                        logger.info("User roles: " + userDetails.getAuthorities());
    
                        // Create the authentication token
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(), 
                                null, 
                                userDetails.getAuthorities()
                        );
    
                        // Set the authentication in the SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        logger.warn("User details could not be retrieved from token");
                    }
                } else {
                    logger.warn("Invalid JWT Token");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid JWT Token");
                    return;
                }
            } catch (Exception e) {
                logger.error("Error occurred during JWT validation: " + e.getMessage(), e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT Token");
                return;
            }
        }
    
        filterChain.doFilter(request, response);
    }
    
}
