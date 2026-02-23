package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.UserService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

   @Bean
    public DaoAuthenticationProvider authProvider(
        PasswordEncoder passwordEncoder,
        UserDetailsService userDetailsService) {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);

    authProvider.setPasswordEncoder(passwordEncoder);
    // authProvider.setHideUserNotFoundExceptions(false);

    return authProvider;
}
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration); 
        return source;
    }
    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(
            org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF cho API
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Đăng ký bảng luật cho Frontend đi vào
            .authorizeHttpRequests(authorize -> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll()

                .requestMatchers(
                    "/",
                    "/api/v1/auth/**",
                    "/api/v1/payment/**",
                    "/v3/api-docs/**",       // Cho phép truy cập Swagger JSON
                    "/swagger-ui/**",        // Cho phép gửi tài nguyên của Swagger UI
                    "/swagger-ui.html"       // Cho phép xem giao diện Swagger
                ).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(sessionManagement -> sessionManagement
                // CHỐT CHẶN REST API: KHÔNG BAO GIỜ TẠO SESSION HỘ KHÁCH HÀNG
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            );
            
        // Thiết lập Chốt chặn kiểm tra JWT Token
        http.addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
