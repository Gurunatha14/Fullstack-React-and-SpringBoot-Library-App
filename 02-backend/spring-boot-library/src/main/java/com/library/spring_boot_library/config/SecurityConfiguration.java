
package com.library.spring_boot_library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@Configuration
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable()) // Disable CSRF protection for stateless APIs
                                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                                .requestMatchers("/api/books/secure/**", "/api/reviews/secure/**",
                                                                "api/messages/secure/**", "/api/admin/secure/**")
                                                .authenticated()

                                                .anyRequest().permitAll() // Allow other requests
                                )
                                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configure CORS
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // Custom
                                                                                                                // entry
                                                                                                                // point
                                )
                                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                                                .jwt(jwt -> jwt.decoder(jwtDecoder())) // Use JWT decoder
                                );

                return http.build();
        }

        @Bean
        public JwtDecoder jwtDecoder() {
                return JwtDecoders.fromIssuerLocation("https://dev-59698608.okta.com/oauth2/default");
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(java.util.Collections.singletonList("http://localhost:3000")); // Allow
                                                                                                               // your
                                                                                                               // frontend
                                                                                                               // origin
                configuration.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "DELETE")); // Allow
                                                                                                          // specific
                                                                                                          // methods
                configuration.setAllowedHeaders(java.util.Arrays.asList("*")); // Allow all headers
                configuration.setAllowCredentials(true); // Allow credentials if needed

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all paths
                return source;
        }

        private static class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
                @Override
                public void commence(HttpServletRequest request, HttpServletResponse response,
                                AuthenticationException authException) throws java.io.IOException {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                        response.getWriter().write(
                                        "{\"error\": \"Unauthorized\", \"message\": \"You are not authorized to access this resource.\"}");
                }
        }
}
