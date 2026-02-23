package com.example.demo.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.domain.DTO.RestResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserAPIController {

    private final UserService userService;

    public UserAPIController(UserService userService) {
        this.userService = userService;
    }

    // Lấy toàn bộ danh sách User (Chỉ Admin mới có quyền gọi cái này)
    @GetMapping
    public ResponseEntity<RestResponse<List<User>>> getAllUsersAPI() {
        RestResponse<List<User>> res = new RestResponse<>();
        
        List<User> users = this.userService.getAllUsers();
        
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Danh sách người dùng hệ thống");
        res.setData(users);

        return ResponseEntity.ok(res);
    }

    // Xóa User theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteUserAPI(@PathVariable Long id) {
        RestResponse<Void> res = new RestResponse<>();
        User user = this.userService.getUserById(id);
        
        if (user == null) {
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setError("Xóa thất bại. Không tìm thấy User ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        
        this.userService.deleteUserById(id);
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Đã xóa User thành công khỏi Database!");
        
        return ResponseEntity.ok(res);
    }
}
