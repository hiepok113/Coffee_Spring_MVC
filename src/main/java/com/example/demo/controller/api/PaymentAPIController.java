package com.example.demo.controller.api;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.DTO.RestResponse;
import com.example.demo.service.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentAPIController {

    private final VNPayService vnPayService;

    public PaymentAPIController(VNPayService vnPayService) {
        this.vnPayService = vnPayService;
    }

    // API #1: Tạo link thanh toán VNPay
    @GetMapping("/create_payment")
    public ResponseEntity<RestResponse<String>> createPayment(HttpServletRequest request, @RequestParam("amount") long amount) throws UnsupportedEncodingException {
        
        // Giao toàn bộ Logic tạo URL hầm hố cho lớp Dịch vụ VNPayService xử lý gọn gàng
        String paymentUrl = vnPayService.createOrder(request, amount, "Thanh toan don hang ma giao dich:");

        RestResponse<String> res = new RestResponse<>();
        res.setStatusCode(200);
        res.setMessage("Tạo Link thanh toán thành công! Vui lòng truy cập đường dẫn sau:");
        res.setData(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    
    // API #2: Cột mốc Callback hứng tín hiệu thành công từ Máy chủ VNPay quay về lại Server của mình
    @GetMapping("/vnpay_return")
    public ResponseEntity<RestResponse<String>> paymentReturn(HttpServletRequest request) {
        // Trong dự án đi làm thực tế, bạn sẽ bắt mã vnp_ResponseCode=00 ở đây 
        // Sau đó gọi hàm orderService.updateOrderStatus(txnRef, "PAID") để đánh dấu đơn hàng.
        
        String responseCode = request.getParameter("vnp_ResponseCode");
        String transactionRef = request.getParameter("vnp_TxnRef");
        
        RestResponse<String> res = new RestResponse<>();
        if ("00".equals(responseCode)) {
            res.setStatusCode(200);
            res.setMessage("Quá tuyệt vời! Thanh toán VNPay thành công mã giao dịch: " + transactionRef);
            res.setData("DB của bạn đáng lẽ vừa được update thành ĐÃ THANH TOÁN (Trừ tiền Sandbox rồi nhé).");
            return ResponseEntity.ok(res);
        } else {
            res.setStatusCode(400);
            res.setError("Thanh toán THẤT BẠI hoặc KHÁCH HÀNG HỦY giao dịch.");
            res.setMessage("Mã lỗi VNPay: " + responseCode);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }
}
