package com.example.demo.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.DTO.RestResponse;
import com.example.demo.service.CartService;

import java.util.stream.Collectors;
import com.example.demo.domain.DTO.ResCartDTO;
import com.example.demo.domain.DTO.ResCartDTO.ResCartDetailDTO;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartAPIController {

    private final CartService cartService;

    public CartAPIController(CartService cartService) {
        this.cartService = cartService;
    }

    // 1. Xem nội dung Giỏ hàng hiện tại (Chỉ hoạt động khi Client đã nhét mã Token vào Header)
    @GetMapping
    public ResponseEntity<RestResponse<ResCartDTO>> getCartAPI() {
        RestResponse<ResCartDTO> res = new RestResponse<>();
        
        // Cạy mã token JWT ra ghép với Context lấy email
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        
        Cart cart = this.cartService.getCartByEmail(email);
        
        if (cart != null) {
            // Tự động chữa lành / Cập nhật tổng số lượng
            int cartSum = 0;
            if (cart.getCartDetails() != null) {
                for (CartDetail cd : cart.getCartDetails()) {
                    cartSum += cd.getQuantity();
                }
            }
            cart.setSum(cartSum);
            this.cartService.updateCartSum(cart);
            
            // Xử lý DTO: Chuyển đổi dữ liệu Thô thành dữ liệu Custom
            ResCartDTO resDTO = new ResCartDTO();
            resDTO.setId(cart.getId());
            resDTO.setSum(cartSum);
            
            if (cart.getCartDetails() != null) {
                List<ResCartDetailDTO> items = cart.getCartDetails().stream().map(cd -> {
                    ResCartDetailDTO detail = new ResCartDetailDTO();
                    detail.setId(cd.getId());
                    detail.setProductName(cd.getProduct().getName());
                    detail.setPrice(cd.getPrice());
                    detail.setQuantity(cd.getQuantity());
                    detail.setImage(cd.getProduct().getImage());
                    return detail;
                }).collect(Collectors.toList());
                resDTO.setItems(items);
            }
            
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Tải dữ liệu giỏ hàng Custom thành công!");
            res.setData(resDTO); // Ném cục DTO sạch sẽ ra thay vì Cart gốc
            return ResponseEntity.ok(res);
        }
        
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage("Giỏ hàng của Email [" + email + "] đang trống nhe cưng!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // 2. Thêm đồ vào Giỏ hàng (dựa trên ID sản phẩm)
    @PostMapping("/add/{id}")
    public ResponseEntity<RestResponse<String>> addProductToCartAPI(@PathVariable long id, @RequestParam(value = "quantity", defaultValue = "1") long quantity) {
        RestResponse<String> res = new RestResponse<>();
        
        // Rút email từ SecurityContext (đã được JwtAuthFilter dọn đường trước)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        
        // Nạp vào giỏ hàng
        this.cartService.handleAddProductToCart(email, id, quantity);
        
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Thêm thành công x" + quantity + " sản phẩm (id=" + id + ") vào giỏ của " + email);
        res.setData("OK");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
