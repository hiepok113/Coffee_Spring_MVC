package com.example.demo.domain.DTO;

import java.util.List;

public class ResOrderDTO {
    private long id;
    private double totalPrice;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String status;
    private List<ResOrderDetailDTO> details;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResOrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<ResOrderDetailDTO> details) {
        this.details = details;
    }

    // Chi tiết phân nhánh hiển thị sản phẩm trong Order
    public static class ResOrderDetailDTO {
        private long id;
        private String productName;
        private double price;
        private long quantity;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }
    }
}
