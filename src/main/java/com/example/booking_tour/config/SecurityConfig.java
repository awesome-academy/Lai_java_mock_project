package com.example.booking_tour.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JwtAuthFilter jwtAuthFilter;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOriginPatterns(Arrays.asList("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                configuration.setAllowedHeaders(Arrays.asList("*"));
                configuration.setAllowCredentials(true);
                configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
                configuration.setMaxAge(3600L);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/api/**", configuration);
                return source;
        }

        /**
         * 0️⃣ Static resources (CSS / JS / Images)
         * Được ưu tiên cao nhất
         */
        @Bean
        @Order(0)
        public SecurityFilterChain staticResourcesSecurityFilterChain(HttpSecurity http) throws Exception {
                http
                                .securityMatcher(
                                                "/css/**",
                                                "/js/**",
                                                "/images/**",
                                                "/fonts/**",
                                                "/favicon.ico",
                                                "/assets/**",
                                                "/index.html")
                                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                                .requestCache(cache -> cache.disable())
                                .securityContext(context -> context.disable())
                                .sessionManagement(session -> session.disable())
                                .csrf(csrf -> csrf.disable());

                return http.build();
        }

        /**
         * 1️⃣ ADMIN – Server-side login dùng session
         */
        @Bean
        @Order(1)
        public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
                http
                                .securityMatcher("/admin/**")
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/admin/login", "/admin/register").permitAll()
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/admin/login")
                                                .loginProcessingUrl("/admin/login")
                                                .defaultSuccessUrl("/admin/dashboard", true)
                                                .failureUrl("/admin/login?error=true")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/admin/logout")
                                                .logoutSuccessUrl("/admin/login?logout=true")
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)
                                                .deleteCookies("JSESSIONID"))
                                .exceptionHandling(exception -> exception
                                                .accessDeniedPage("/admin/login?error=forbidden"))
                                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

                return http.build();
        }

        /**
         * 2️⃣ API JWT – không dùng session (stateless)
         * Áp dụng cho /api/**
         */
        @Bean
        @Order(2)
        public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
                http
                                .securityMatcher("/api/**")
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                                                .requestMatchers("/api/auth/login",
                                                                "/api/auth/register",
                                                                "/api/auth/me",
                                                                "/api/tours",
                                                                "/api/tours/{id}",
                                                                "/api/tours/top-locations",
                                                                "/api/upload/**",
                                                                "/uploads/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        /**
         * 3️⃣ USER WEBSITE – server-side login
         */
        @Bean
        @Order(3)
        public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
                http
                                .securityMatcher("/**")
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**",
                                                                "/images/**")
                                                .permitAll()
                                                .requestMatchers("/profile/**", "/booking/**")
                                                .hasRole("USER")
                                                .anyRequest()
                                                .permitAll())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/login?error=true")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/?logout=true")
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)
                                                .deleteCookies("JSESSIONID"))
                                .rememberMe(remember -> remember
                                                .key("uniqueAndSecret")
                                                .tokenValiditySeconds(86400)
                                                .rememberMeParameter("remember-me"));

                return http.build();
        }
}
