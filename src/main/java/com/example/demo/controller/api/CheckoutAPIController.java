package com.example.demo.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.domain.DTO.CheckoutDTO;
import com.example.demo.domain.DTO.RestResponse;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutAPIController {

    private final OrderService orderService;
    private final UserService userService;

    public CheckoutAPIController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // Tiến hành thanh toán (Tạo Order từ Cart hiện tại)
    @PostMapping
    public ResponseEntity<RestResponse<String>> handlePlaceOrderAPI(@RequestBody @Valid CheckoutDTO checkoutDTO) {
        RestResponse<String> res = new RestResponse<>();
        
        // 1. Lọc lấy Email người dùng qua tấm vé Token trong Header
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        
        // 2. Tìm User trong DB
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            res.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            res.setError("Không xác định được danh tính người thanh toán.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        
        // 3. Tiến hành đặt hàng (Chuyển hàng từ Cart sang Order). 
        // Pass "null" vào vị trí HttpSession vì API không xài session.
        this.orderService.handlePlaceOrder(
            user, 
            null, 
            checkoutDTO.getReceiverName(), 
            checkoutDTO.getReceiverAddress(), 
            checkoutDTO.getReceiverPhone()
        );
        
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Hoàn tất thanh toán. Đơn hàng của bạn đã được ghi nhận!");
        res.setData("Tạo Order thành công. Giỏ hàng đã được làm rỗng.");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
