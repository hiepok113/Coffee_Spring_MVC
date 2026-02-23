package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 1. Chỉ bắt filter các API request, bỏ qua các MVC web thông thường
        String uri = request.getRequestURI();
        if (!uri.startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Chặn các request gửi Authentication header lên để kiểm tra
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return ;
        }

        // Cắt bỏ chữ "Bearer " (7 kí tự đầu)
        jwtToken = authHeader.substring(7);
        try {
            userEmail = jwtUtil.extractUsername(jwtToken);
            
            // Nếu có email trong token mà Spring chưa ghi nhận đăng nhập
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                
                // Giám định token xem có hợp lệ và hết hạn chưa
                if (jwtUtil.isTokenValid(jwtToken, userDetails)) {
                    // Nếu đúng, cho qua chốt kiểm soát của Spring Security
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing JWT token: " + e.getMessage());
        }
        
        filterChain.doFilter(request, response);
    }
}
