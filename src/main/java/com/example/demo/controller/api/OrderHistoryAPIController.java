package com.example.demo.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.domain.DTO.ResOrderDTO;
import com.example.demo.domain.DTO.ResOrderDTO.ResOrderDetailDTO;
import com.example.demo.domain.DTO.RestResponse;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/history")
public class OrderHistoryAPIController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderHistoryAPIController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<ResOrderDTO>>> getOrderHistoryAPI() {
        RestResponse<List<ResOrderDTO>> res = new RestResponse<>();
        
        // 1. Dùng bùa "JWT Filter" moi Email người dùng từ Security Context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        
        // 2. Tìm User trong DB
        User user = this.userService.getUserByEmail(email);
        
        // 3. Lôi danh sách đơn hàng gốc (kèm theo rác) từ MySQL ra
        List<Order> rawOrders = this.orderService.fetchOrderByUser(user);
        
        // 4. Biến hình List<Order> thành List<ResOrderDTO> siêu gọn nhẹ
        List<ResOrderDTO> cleanOrders = rawOrders.stream().map(order -> {
            ResOrderDTO dto = new ResOrderDTO();
            dto.setId(order.getId());
            dto.setReceiverName(order.getReceiverName());
            dto.setReceiverAddress(order.getReceiverAddress());
            dto.setReceiverPhone(order.getReceiverPhone());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());
            
            if (order.getOrderDetails() != null) {
                List<ResOrderDetailDTO> details = order.getOrderDetails().stream().map(od -> {
                    ResOrderDetailDTO detailDTO = new ResOrderDetailDTO();
                    detailDTO.setId(od.getId());
                    detailDTO.setProductName(od.getProduct().getName());
                    detailDTO.setPrice(od.getPrice());
                    detailDTO.setQuantity(od.getQuantity());
                    return detailDTO;
                }).collect(Collectors.toList());
                dto.setDetails(details);
            }
            
            return dto;
        }).collect(Collectors.toList());
        
        // 5. Nạp đạn và bắn trả về Frontend
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Tải lịch sử mua hàng của bạn thành công!");
        res.setData(cleanOrders);
        
        return ResponseEntity.ok(res);
    }
}
