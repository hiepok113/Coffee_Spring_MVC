package com.example.demo.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.domain.DTO.RestResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionController {

    // 1. Chuyên xử lý lỗi Kiểm định Dữ liệu (Validation Error) từ annotation @Valid
    // Ví dụ: Bỏ trống tên SP, Giá tiền âm, thiếu mật khẩu...
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value()); // Mã 400 - Do client gửi bậy 
        res.setError("Dữ liệu đầu vào không hợp lệ (Validation Error)");

        // Quét tất cả các lỗi và gom thành 1 danh sách gửi cho React/Mobile in ra màn hình
        List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
        res.setMessage(errors.size() > 1 ? errors : errors.get(0));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
    
    // 2. Chuyên Tóm Gọn tất cả các lỗi còn lại (Crashes, NullPointerException...)
    // Để server không lòi code lỗi Java ra ngoài (Bảo mật 100%)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestResponse<Object>> handleAllException(Exception ex) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); // Mã 500
        res.setMessage(ex.getMessage());
        res.setError("Internal Server Error - Hệ thống đang bảo trì hoặc gặp sự cố.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
