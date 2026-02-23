package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Cafe & Wash REST API (Spring Boot 3)",
                version = "1.0.0",
                description = "Hệ thống API quản lý Bán Hàng E-commerce, tích hợp thanh toán VNPay và bảo mật JWT Stateless.",
                contact = @Contact(
                        name = "Thanh Hiep",
                        email = "thanhhiep.hongduc@gmail.com"
                )
        ),
        security = {
                @SecurityRequirement(name = "BearerAuth")
        }
)
@SecurityScheme(
        name = "BearerAuth", // Tên của scheme, phải map với @SecurityRequirement bên trên
        description = "Nhập JWT Token lấy từ API Login vào đây để mở khóa các Endpoint bảo mật.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    // Lớp này dùng để trang điểm cho giao diện Swagger UI thêm chuyên nghiệp
    // và kích hoạt ổ khóa màu xanh (Authorize) để chèn JWT Token.
}
