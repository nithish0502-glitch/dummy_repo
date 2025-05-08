package com.examly.springappfeedback.config;

import com.examly.springappfeedback.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtValidationService {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationService.class);

    private static final String secretKey = "java";

    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate;

    @Autowired
    public JwtValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getUserServiceUrl() {
        return discoveryClient.getInstances("user-service").stream()
            .findFirst()
            .map(instance -> instance.getUri().toString())
            .orElseThrow(() -> new RuntimeException("User service not found"));
    }


    public boolean validateToken(String token) {
        String userServiceUrl = getUserServiceUrl();
        logger.debug("User service URL: {}", userServiceUrl);

        String url = UriComponentsBuilder.fromHttpUrl(userServiceUrl + "/api/users/validate-token")
                .queryParam("token", token)
                .toUriString();

        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    Boolean.class
            );

            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            logger.error("Error validating token", e);
            return false;
        }
    }

    public User getUserById(int userId) {
        String userServiceUrl = getUserServiceUrl();
        String url = UriComponentsBuilder.fromHttpUrl(userServiceUrl + "/api/users/" + userId)
                .toUriString();

        try {
            ResponseEntity<User> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    User.class
            );

            return response.getBody();
        } catch (Exception e) {
            logger.error("Error fetching user by ID", e);
            return null;
        }
    }
// public UserDetails getUserDetailsFromToken(String token) {
//     // Extract claims from token and create UserDetails object
//     Claims claims = Jwts.parser()
//         .setSigningKey(secretKey)
//         .parseClaimsJws(token)
//         .getBody();

//     String username = claims.getSubject();
//     List<SimpleGrantedAuthority> authorities = ((List<?>) claims.get("roles")).stream()
//         .map(role -> new SimpleGrantedAuthority((String) role))
//         .collect(Collectors.toList());

//     return new org.springframework.security.core.userdetails.User(username, "", authorities);
// }

public UserDetails getUserDetailsFromToken(String token) {
    // Log the token being processed
    logger.debug("Processing token: {}", token);

    Claims claims = Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody();

    String username = claims.getSubject();
    List<SimpleGrantedAuthority> authorities = ((List<?>) claims.get("roles")).stream()
        .map(role -> new SimpleGrantedAuthority((String) role))
        .collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(username, "", authorities);
}



    private int extractUserIdFromToken(String token) {
        // Implement JWT parsing logic to extract user ID from token
        return 0; // Example implementation
    }
}
