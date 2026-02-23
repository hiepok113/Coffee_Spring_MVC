package com.example.demo.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.domain.DTO.LoginDTO;
import com.example.demo.domain.DTO.RegisterDTO;
import com.example.demo.domain.DTO.RestResponse;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthAPIController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthAPIController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ========== API REGISTER ==========
    @PostMapping("/register")
    public ResponseEntity<RestResponse<User>> register(@RequestBody @Valid RegisterDTO registerDTO) {
        RestResponse<User> res = new RestResponse<>();
        
        // Kiểm tra xem email có tồn tại chưa (Bạn có thể bỏ qua bước này nếu trong Repository đã quy định Unique)
        if (this.userService.getUserByEmail(registerDTO.getEmail()) != null) {
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            res.setError("Validation Error");
            res.setMessage("Email đã được sử dụng. Vui lòng chọn Email khác!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        User user = this.userService.registerDTOtoUser(registerDTO);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(this.userService.getRoleByName("USER")); // Mặc định khách hàng API là USER
        
        User savedUser = this.userService.handleSaveUser(user);
        
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Đăng ký tài khoản hệ thống API thành công!");
        res.setData(savedUser); // Thường người ta sẽ trả DTO khác để ẩn passWord, nhưng học tạm thì ném thẳng user

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // ========== API LOGIN ==========
    @PostMapping("/login")
    public ResponseEntity<RestResponse<String>> authenticate(@RequestBody @Valid LoginDTO loginDTO) {
        // Nhờ bộ phận an ninh của Spring Security check hộ mật khẩu ngay trong Database 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        
        // Đã chứng nhận an toàn (Vượt chốt ở Auth Manager)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Máy phát điện JwtUtil bắt đầu in cho 1 tờ giấy thông hành (Token mã hóa cứng)
        String jwtToken = jwtUtil.generateToken(userDetails);
        
        RestResponse<String> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Đăng nhập bằng Email/Password thành công!");
        res.setData(jwtToken);
        
        return ResponseEntity.ok(res);
    }
}
