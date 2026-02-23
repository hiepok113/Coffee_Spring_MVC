package com.example.demo.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CheckoutDTO {

    @NotBlank(message = "Tên người nhận không được để trống!")
    private String receiverName;

    @NotBlank(message = "Địa chỉ nhận hàng không được để trống!")
    private String receiverAddress;

    @NotBlank(message = "Số điện thoại nhận hàng là bắt buộc!")
    @Size(min=10, message="Số điện thoại phải từ 10 số trở lên")
    private String receiverPhone;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
